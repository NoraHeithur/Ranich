package com.example.ranich.ui.screen.help_graph

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ranich.common.SessionManager
import com.example.ranich.domain.common.SensorObserver
import com.example.ranich.domain.model.AlertType
import com.example.ranich.domain.model.PostFallingAlert
import com.example.ranich.domain.model.SensorType
import com.example.ranich.domain.repository.AlertRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HelpViewModel(
    private val sessionManager: SessionManager,
    private val sensorObserver: SensorObserver,
    private val alertRepo: AlertRepositoryImpl
) : ViewModel() {

    var sensorGyroDetect = MutableStateFlow(false)
        private set

    var sensorAccelDetect = MutableStateFlow(false)
        private set

    init {
        sensorObserver.onGyroscopeSensorResponse(viewModelScope) { isDetected ->
            if (isDetected) {
                sensorGyroDetect.emit(isDetected)
            }
        }

        sensorObserver.onAccelerometerResponse(viewModelScope) { isDetected ->
            if (isDetected) {
                sensorAccelDetect.emit(isDetected)
            }
        }
    }

    fun manualRequestForHelp() {
        viewModelScope.launch {
            alertRepo.requestForHelp(alertManualBuilder())
        }
    }

    fun requestForHelpByAccelerometer() {
        viewModelScope.launch {
            alertRepo.requestForHelp(alertDetectBuilder(sensor = SensorType.ACCLE))
        }
    }

    fun requestForHelpByGyroscope() {
        viewModelScope.launch {
            alertRepo.requestForHelp(alertDetectBuilder(sensor = SensorType.GYRO))
        }
    }

    fun cancelRequest() {
        viewModelScope.launch {
            alertRepo.requestForHelp(alertCancelBuilder())
            sensorGyroDetect.emit(false)
            sensorAccelDetect.emit(false)
        }
    }

    fun restartSensor() {
        sensorObserver.startSensor()
    }

    fun stopSensor() {
        sensorObserver.stopSensor()
    }

    private fun alertDetectBuilder(sensor: SensorType) : PostFallingAlert {
        return PostFallingAlert(
            fromUserId = sessionManager.getUserUUID(),
            type = AlertType.DETECT,
            sensor = sensor
        )
    }

    private fun alertManualBuilder() : PostFallingAlert {
        return PostFallingAlert(
            fromUserId = sessionManager.getUserUUID(),
            type = AlertType.MANUAL,
            sensor = null
        )
    }

    private fun alertCancelBuilder() : PostFallingAlert {
        return PostFallingAlert(
            fromUserId = sessionManager.getUserUUID(),
            type = AlertType.OK,
            sensor = null
        )
    }
}