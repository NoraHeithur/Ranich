package com.example.ranich.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Address(
    val name: String,
    val details: String,
    val province: String,
    val country: String,
    val zipCode: String,
) : Parcelable
