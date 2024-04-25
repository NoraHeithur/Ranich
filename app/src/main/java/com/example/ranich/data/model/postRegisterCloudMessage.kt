package com.example.ranich.data.model

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class PostRegisterCloudMessage(
    @Json(name = "firebaseToken")
    val firebaseToken: String,
    @Json(name = "userId")
    val userId: String
)
