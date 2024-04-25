package com.example.ranich.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class FallingAlertModel(
    @Json(name = "userId")
    val fromUserId: String,
    @Json(name = "type")
    val type: String,
    @Json(name = "_id")
    val id: String
) : Parcelable