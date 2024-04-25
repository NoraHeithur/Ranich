package com.example.ranich.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AccelerometerDataModel(
    val x: Double,
    val y: Double,
    val z: Double
) : Parcelable