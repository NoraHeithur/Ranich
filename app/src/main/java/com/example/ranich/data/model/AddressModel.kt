package com.example.ranich.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class AddressModel(
    @Json(name = "name")
    val name: String,
    @Json(name = "details")
    val details: String,
    @Json(name = "province")
    val province: String,
    @Json(name = "country")
    val country: String,
    @Json(name = "zipCode")
    val zipCode: String
) : Parcelable