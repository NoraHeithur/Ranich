package com.example.ranich.domain.repository

import com.example.ranich.common.SessionManager
import com.example.ranich.data.model.LoginModel
import com.example.ranich.data.network.RanichServicesClient
import com.example.ranich.di.DispatcherIO
import kotlinx.coroutines.withContext

interface LogInRepository {
    suspend fun logIN(loginModel: LoginModel)
}

class LogInRepositoryImpl(
    private val ranichClient: RanichServicesClient,
    private val sessionManager: SessionManager,
    private val dispatcherIO: DispatcherIO,
) : LogInRepository {

    override suspend fun logIN(loginModel: LoginModel) {
        withContext(dispatcherIO) {
            sessionManager.saveUserUUID(ranichClient.login(loginModel).body()?.id ?: "")
        }
    }
}