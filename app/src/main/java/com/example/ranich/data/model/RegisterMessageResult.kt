package com.example.ranich.data.model

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class RegisterMessageResult(
    @Json(name = "status")
    val status: Int,
    @Json(name = "code")
    val code: Int,
    @Json(name = "message")
    val message: String,
    @Json(name = "description")
    val description: String
)
