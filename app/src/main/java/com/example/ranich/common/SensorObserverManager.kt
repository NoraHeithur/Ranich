package com.example.ranich.common

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import com.example.ranich.domain.common.SensorObserver
import com.example.ranich.domain.common.SensorType
import com.example.ranich.domain.model.SensorAxis
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

class SensorObserverManager(
    context: Context,
) : SensorObserver, SensorEventListener, KoinComponent {

    private lateinit var gyroscopeSensor: Sensor
    private lateinit var accelerometerSensor: Sensor
    private lateinit var sensorManager: SensorManager

    private val gyroscopeDataState = MutableStateFlow(
        SensorAxis(
            x = 0.0,
            y = 0.0,
            z = 0.0,
            sensorType = SensorType.GYROSCOPE
        )
    )
    private val accelerometerDataState = MutableStateFlow(
        SensorAxis(
            x = 0.0,
            y = 0.0,
            z = 0.0,
            sensorType = SensorType.ACCELEROMETER
        )
    )

    private val accelerometerDetected = MutableStateFlow(false)

    private val gyroscopeDetected = MutableStateFlow(false)

    init {
        setupSensors(context)
        startSensor()
    }

    private var accelerometerThreshold = 15.0f
    private var alpha = 0.8f
    private var lastAccValues: FloatArray? = null

    private var gyroscopeThreshold = 3.0f
    private var lowPassFiltersAlpha = 0.2f
    private var lowPassFilters = setLowPassFilter()

    private var previousValues = FloatArray(3)

    private fun setLowPassFilter(alpha: Float = lowPassFiltersAlpha): FloatArray {
        return floatArrayOf(alpha, alpha, alpha)
    }

    private fun setupSensors(context: Context) {
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)?.let {
            gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)!!
        }

        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)?.let {
            accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!!
        }
    }

    override fun onGyroscopeSensorResponse(
        scope: CoroutineScope,
        isDetected: suspend (Boolean) -> Unit,
    ) {
        object : suspend (Flow<Boolean>) -> Unit {
            override suspend fun invoke(isFallen: Flow<Boolean>) {
                scope.launch {
                    isFallen.collect {
                        isDetected(it)
                    }
                }
            }
        }.apply {
            scope.launch {
                val isFallen = gyroscopeDetected.combine(gyroscopeDetected) { gyro, gyro2 ->
                    if (gyro) {
                        stopSensor()
                    }
                    gyro
                }
                invoke(isFallen)
            }
        }
    }

    override fun onAccelerometerResponse(
        scope: CoroutineScope,
        isDetected: suspend (Boolean) -> Unit,
    ) {
        object : suspend (Flow<Boolean>) -> Unit {
            override suspend fun invoke(isFallen: Flow<Boolean>) {
                scope.launch {
                    isFallen.collect {
                        isDetected(it)
                    }
                }
            }
        }.apply {
            scope.launch {
                val isFallen = accelerometerDetected.combine(accelerometerDetected) { acc, acc2 ->
                    if (acc) {
                        stopSensor()
                    }
                    acc
                }
                invoke(isFallen)
            }
        }
    }

    override fun setThreshold(
        accelerometer: Float,
        accelerometerAlpha: Float,
        gyroscope: Float,
        gyroscopeAlpha: Float,
    ) {
        stopSensor()

        this.accelerometerThreshold = accelerometer
        this.alpha = accelerometerAlpha
        this.gyroscopeThreshold = gyroscope
        this.lowPassFiltersAlpha = gyroscopeAlpha
        this.lowPassFilters = setLowPassFilter(this.lowPassFiltersAlpha)

        startSensor()
    }

    override fun startSensor() {
        if (sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) != null) {
            sensorManager.registerListener(
                this,
                gyroscopeSensor,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }

        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            sensorManager.registerListener(
                this,
                accelerometerSensor,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }
    }

    override fun stopSensor() {
        sensorManager.unregisterListener(
            this,
            gyroscopeSensor
        )
        sensorManager.unregisterListener(
            this,
            accelerometerSensor
        )
    }

    private fun applyLowPassFilter(data: FloatArray): FloatArray {
        if (lastAccValues == null) {
            lastAccValues = data.copyOf()
            return data
        }

        for (i in data.indices) {
            if (data[i] != lastAccValues!![i]) {
                lastAccValues!![i] = lastAccValues!![i] + alpha * (data[i] - lastAccValues!![i])
            }
        }
        return lastAccValues!!
    }

    private fun setGyroscopeAxisData(event: SensorEvent) {
        gyroscopeDataState.value = event.values?.let {
            SensorAxis(
                x = it[0].toDouble(),
                y = it[1].toDouble(),
                z = it[2].toDouble(),
                sensorType = SensorType.GYROSCOPE
            )
        } ?: run {
            SensorAxis(
                x = 0.0,
                y = 0.0,
                z = 0.0,
                sensorType = SensorType.GYROSCOPE
            )
        }
    }

    private fun setAccelerometerAxisData(event: SensorEvent) {
        accelerometerDataState.value = applyLowPassFilter(event.values).let {
            SensorAxis(
                x = it[0].toDouble(),
                y = it[1].toDouble(),
                z = it[2].toDouble(),
                sensorType = SensorType.ACCELEROMETER
            )
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        when (event?.sensor?.type) {
            Sensor.TYPE_GYROSCOPE -> {
                setGyroscopeAxisData(event)
                for (i in 0 until 3) {
                    val value = event.values[i]
                    val filteredValue = filter(lowPassFilters[i], value)

                    val delta = abs(filteredValue - previousValues[i])

                    gyroscopeDetected.value = delta > gyroscopeThreshold

                    previousValues[i] = filteredValue
                }
            }

            Sensor.TYPE_ACCELEROMETER -> {
                setAccelerometerAxisData(event)
                val accelerometerData = accelerometerDataState.value
                val acceleration = sqrt(
                    accelerometerData.x.pow(
                        2.0
                    ) + accelerometerData.y.pow(
                        2.0
                    ) + accelerometerData.z.pow(
                        2.0
                    )
                )
                accelerometerDetected.value = acceleration > accelerometerThreshold
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // do nothing
    }

    private fun filter(alpha: Float, value: Float): Float {
        var previousValue = 0f

        previousValue += alpha * (value - previousValue)
        return previousValue
    }
}