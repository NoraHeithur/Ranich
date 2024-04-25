package com.example.ranich.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class HospitalContactModel(
    @Json(name = "_id")
    val id: String,
    @Json(name = "userId")
    val userId: String,
    @Json(name = "hospitalName")
    val hospitalName: String,
    @Json(name = "doctorName")
    val doctorName: String,
    @Json(name = "contactNumber")
    val contactNumber: String,
) : Parcelable

@Keep
data class PostHospitalContactModel(
    @Json(name = "userId")
    val userId: String,
    @Json(name = "hospitalName")
    val hospitalName: String,
    @Json(name = "doctorName")
    val doctorName: String,
    @Json(name = "contactNumber")
    val contactNumber: String
)