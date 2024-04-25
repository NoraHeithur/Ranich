package com.example.ranich.ui.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ranich.data.model.LoginModel
import com.example.ranich.domain.repository.LogInRepository
import kotlinx.coroutines.launch

class LogInViewModel(
    private val logInRepository: LogInRepository,
) : ViewModel() {

    fun logIn(userName: String, password: String) {
        viewModelScope.launch {
            logInRepository.logIN(LoginModel(userName, password))
        }
    }
}

