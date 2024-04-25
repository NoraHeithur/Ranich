package com.example.ranich.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hospital(
    val id: String,
    val userId: String,
    val hospitalName: String,
    val doctorName: String,
    val contactNumber: String,
) : Parcelable