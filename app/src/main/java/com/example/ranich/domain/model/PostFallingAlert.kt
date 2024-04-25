package com.example.ranich.domain.model

data class PostFallingAlert(
    val fromUserId: String,
    val type: AlertType = AlertType.DETECT,
    val sensor: SensorType?
)

enum class AlertType {
    DETECT,
    MANUAL,
    OK
}

enum class SensorType {
    GYRO,
    ACCLE,
}