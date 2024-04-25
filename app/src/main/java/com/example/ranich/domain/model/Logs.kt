package com.example.ranich.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Logs(
    val header: String,
    val message: String,
    val phoneNumber: String,
    val timestamp: String,
) : Parcelable