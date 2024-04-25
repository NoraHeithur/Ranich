package com.example.ranich.domain.common

import kotlinx.coroutines.CoroutineScope

interface SensorObserver {
    fun startSensor()
    fun stopSensor()
    fun onGyroscopeSensorResponse(
        scope: CoroutineScope,
        isDetected: suspend (Boolean) -> Unit,
    )

    fun onAccelerometerResponse(
        scope: CoroutineScope,
        isDetected: suspend (Boolean) -> Unit,
    )

    fun setThreshold(
        accelerometer: Float = 15.0f,
        accelerometerAlpha: Float = 0.8f,
        gyroscope: Float = 3.0f,
        gyroscopeAlpha: Float = 0.2f,
    )
}