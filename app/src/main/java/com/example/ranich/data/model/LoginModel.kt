package com.example.ranich.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginModel(
    @Json(name = "username")
    val username: String,
    @Json(name = "password")
    val password: String
)