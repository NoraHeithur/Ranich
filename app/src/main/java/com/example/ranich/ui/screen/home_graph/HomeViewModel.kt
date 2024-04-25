package com.example.ranich.ui.screen.home_graph

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ranich.common.SessionManager
import com.example.ranich.domain.model.ContactData
import com.example.ranich.domain.model.UserProfile
import com.example.ranich.domain.repository.ContactRepository
import com.example.ranich.domain.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val sessionManager: SessionManager,
    private val userInfo: UserRepository,
    private val contacts: ContactRepository,
) : ViewModel() {

    var contactData = MutableStateFlow(ContactData(emptyList()))
        private set
    var logs = MutableStateFlow(ContactData(emptyList()))
        private set

    init {
        viewModelScope.launch {
            contactData.emit(
                ContactData(
                    contacts = contacts.getHospitalContact().contacts + contacts.getPersonalContact().contacts
                )
            )
            logs.emit(contacts.getLogs())
        }
    }

    fun getUserInfo(execute: suspend (UserProfile) -> Unit) {
        viewModelScope.launch {
            execute(userInfo.getUserInfo(sessionManager.getUserUUID()))
        }
    }
}