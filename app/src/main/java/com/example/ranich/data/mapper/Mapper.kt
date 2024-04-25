package com.example.ranich.data.mapper

import com.example.ranich.data.model.AddressModel
import com.example.ranich.data.model.HospitalContactModel
import com.example.ranich.data.model.LogsModel
import com.example.ranich.data.model.PersonalContactModel
import com.example.ranich.data.model.PostFallingAlertModel
import com.example.ranich.data.model.UserProfileModel
import com.example.ranich.domain.model.Address
import com.example.ranich.domain.model.Contact
import com.example.ranich.domain.model.PostFallingAlert
import com.example.ranich.domain.model.UserProfile
import com.example.ranich.ui.component.card.CardType
import java.util.UUID

fun UserProfileModel.toDomainModel(): UserProfile {
    return UserProfile(
        id = this.id,
        fullName = "${this.firstname} ${this.lastname}",
        age = this.age,
        gender = this.gender,
        addressModel = this.addressModel.toDomainModel(),
        phoneNumber = this.phoneNumber,
        treatmentHistory = this.treatmentHistory
    )
}

fun AddressModel.toDomainModel(): Address {
    return Address(
        name = this.name,
        details = this.details,
        province = this.province,
        country = this.country,
        zipCode = this.zipCode
    )
}

fun PersonalContactModel.toDomainModel(): Contact {
    return Contact(
        contactId = this.contactId,
        name = "${this.contact.firstname} ${this.contact.lastname}",
        relationship = this.relationship,
        contactNumber = this.contact.phoneNumber,
        isEmergency = isEmergency,
        cardType = CardType.EMERGENCY
    )
}

fun HospitalContactModel.toDomainModel(): Contact {
    return Contact(
        contactId = this.id,
        name = this.doctorName,
        relationship = this.hospitalName,
        contactNumber = this.contactNumber,
        isEmergency = true,
        cardType = CardType.HOSPITAL
    )
}

fun LogsModel.toDomainModel(): Contact {
    return Contact(
        contactId = UUID.randomUUID().toString(),
        name = this.message,
        relationship = this.timestamp,
        contactNumber = this.phoneNumber,
        isEmergency = false,
        cardType = when (this.header) {
            "ฉันปกติดี", "แจ้งเตือนตรวจจับการล้ม" -> CardType.FALLING
            else -> CardType.HELP
        }
    )
}

fun PostFallingAlert.toNetworkModel(): PostFallingAlertModel {
    return PostFallingAlertModel(
        fromUserId = fromUserId,
        type = type.name,
        sensor = sensor?.name?.lowercase()
    )
}