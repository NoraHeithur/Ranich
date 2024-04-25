package com.example.ranich.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserProfile(
    val id: String,
    val fullName: String,
    val age: String,
    val gender: String,
    val addressModel: Address,
    val phoneNumber: String,
    val treatmentHistory: String
) : Parcelable