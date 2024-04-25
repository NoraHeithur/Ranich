package com.example.ranich.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


@JsonClass(generateAdapter = true)
@Parcelize
data class ContactModel(
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

@Keep
data class PostContactModel(
    @Json(name = "userId")
    val userId: String,
    @Json(name = "contactId")
    val contactId: String,
    @Json(name = "relationship")
    val relationship: String,
    @Json(name = "isEmergency")
    val isEmergency: Boolean
)