package com.example.ranich.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class UserProfileModel(
    @Json(name = "_id")
    val id: String,
    @Json(name = "username")
    val username: String,
    @Json(name = "password")
    val password: String,
    @Json(name = "firstname")
    val firstname: String,
    @Json(name = "lastname")
    val lastname: String,
    @Json(name = "age")
    val age: String,
    @Json(name = "gender")
    val gender: String,
    @Json(name = "address")
    val addressModel: AddressModel,
    @Json(name = "phoneNumber")
    val phoneNumber: String,
    @Json(name = "treatmentHistory")
    val treatmentHistory: String
) : Parcelable

