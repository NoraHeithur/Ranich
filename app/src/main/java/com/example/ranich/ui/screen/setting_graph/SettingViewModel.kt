package com.example.ranich.ui.screen.setting_graph

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ranich.common.SessionManager
import com.example.ranich.data.model.PostContactModel
import com.example.ranich.data.model.PostHospitalContactModel
import com.example.ranich.domain.model.Address
import com.example.ranich.domain.model.ContactData
import com.example.ranich.domain.model.UserProfile
import com.example.ranich.domain.repository.ContactRepository
import com.example.ranich.domain.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingViewModel(
    private val sessionManager: SessionManager,
    private val userInfo: UserRepository,
    private val contact: ContactRepository,
) : ViewModel() {

    val userInfoState = MutableStateFlow(
        UserProfile(
            "",
            "",
            "",
            "",
            Address(
                "",
                "",
                "",
                "",
                "",
            ),
            "",
            "",
        )
    )

    val contactData = MutableStateFlow(ContactData(emptyList()))

    init {
        getUserInfo()
        getAllContact()
    }

    private fun getUserInfo() {
        viewModelScope.launch {
            val userInfo = userInfo.getUserInfo(sessionManager.getUserUUID())
            userInfoState.update {
                it.copy(
                    id = userInfo.id,
                    fullName = userInfo.fullName,
                    age = userInfo.age,
                    gender = userInfo.gender,
                    addressModel = userInfo.addressModel,
                    phoneNumber = userInfo.phoneNumber,
                    treatmentHistory = userInfo.treatmentHistory
                )
            }

        }
    }

    private fun getAllContact() {
        viewModelScope.launch {
            contactData.update {
                it.copy(
                    contacts = ContactData(
                        contacts = contact.getHospitalContact().contacts + contact.getPersonalContact().contacts
                    ).contacts
                )
            }
        }
    }

    fun addPersonalContact(contactID: String, relationship: String) {
        viewModelScope.launch {
            contact.postPersonalContact(personalContactBuilder(contactID, relationship))
        }
    }

    fun addHospitalContact(hospitalName: String, doctorName: String, contactNumber: String) {
        viewModelScope.launch {
            contact.postHospitalContact(
                hospitalContactBuilder(
                    hospitalName,
                    doctorName,
                    contactNumber
                )
            )
        }
    }

    private fun personalContactBuilder(contactID: String, relationship: String): PostContactModel {
        return PostContactModel(
            userId = sessionManager.getUserUUID(),
            contactId = contactID,
            relationship = relationship,
            isEmergency = false
        )
    }

    private fun hospitalContactBuilder(
        hospitalName: String,
        doctorName: String,
        contactNumber: String,
    ): PostHospitalContactModel {
        return PostHospitalContactModel(
            userId = sessionManager.getUserUUID(),
            hospitalName = hospitalName,
            doctorName = doctorName,
            contactNumber = contactNumber
        )
    }
}
