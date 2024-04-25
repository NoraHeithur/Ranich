package com.example.ranich.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class LogsModel(
    @Json(name = "header")
    val header: String,
    @Json(name = "message")
    val message: String,
    @Json(name = "phoneNumber")
    val phoneNumber: String,
    @Json(name = "timestamp")
    val timestamp: String,
) : Parcelable