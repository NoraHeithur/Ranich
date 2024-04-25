package com.example.ranich.domain.repository

import com.example.ranich.data.mapper.toNetworkModel
import com.example.ranich.data.network.RanichServicesClient
import com.example.ranich.di.DispatcherIO
import com.example.ranich.domain.model.PostFallingAlert
import kotlinx.coroutines.withContext

interface AlertRepository {
    suspend fun requestForHelp(request: PostFallingAlert)
}

class AlertRepositoryImpl(
    private val ranichApi: RanichServicesClient,
    private val dispatcherIO: DispatcherIO
) : AlertRepository {

    override suspend fun requestForHelp(request: PostFallingAlert) {
        withContext(dispatcherIO) {
            ranichApi.postFallingAlert(request.toNetworkModel())
        }
    }
}