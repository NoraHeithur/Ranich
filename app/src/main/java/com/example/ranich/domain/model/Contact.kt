package com.example.ranich.domain.model

import android.os.Parcelable
import com.example.ranich.ui.component.card.CardType
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContactData(
    val contacts: List<Contact>,
) : Parcelable

@Parcelize
data class Contact(
    val contactId: String,
    val name: String,
    val relationship: String,
    val contactNumber: String,
    var isEmergency: Boolean,
    val cardType: CardType,
) : Parcelable

