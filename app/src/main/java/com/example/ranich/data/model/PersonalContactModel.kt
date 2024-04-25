package com.example.ranich.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class PersonalContactModel(
    @Json(name = "_id")
    val id: String,
    @Json(name = "userId")
    val userId: String,
    @Json(name = "contactId")
    val contactId: String,
    @Json(name = "relationship")
    val relationship: String,
    @Json(name = "isEmergency")
    val isEmergency: Boolean,
    @Json(name = "contact")
    val contact: ContactModel,
) : Parcelable
