package com.example.ranich.domain.model

import android.os.Parcelable
import com.example.ranich.domain.common.SensorType
import kotlinx.parcelize.Parcelize

@Parcelize
data class SensorAxis(
    val x: Double,
    val y: Double,
    val z: Double,
    val sensorType: SensorType
) : Parcelable
