package com.example.ranich.domain.repository

import com.example.ranich.common.ApiResult
import com.example.ranich.common.SessionManager
import com.example.ranich.common.toResult
import com.example.ranich.data.mapper.toDomainModel
import com.example.ranich.data.model.PostContactModel
import com.example.ranich.data.model.PostHospitalContactModel
import com.example.ranich.data.network.RanichServicesClient
import com.example.ranich.di.DispatcherIO
import com.example.ranich.domain.model.ContactData
import kotlinx.coroutines.withContext

interface ContactRepository {
    suspend fun getPersonalContact(): ContactData
    suspend fun getHospitalContact(): ContactData
    suspend fun getLogs(): ContactData
    suspend fun postPersonalContact(contact: PostContactModel)
    suspend fun postHospitalContact(contact: PostHospitalContactModel)
}

class ContactRepositoryImpl(
    sessionManager: SessionManager,
    private val ranichApi: RanichServicesClient,
    private val dispatcherIO: DispatcherIO,
) : ContactRepository {

    private val userId = sessionManager.getUserUUID()

    override suspend fun getPersonalContact(): ContactData {
        val data: ContactData
        withContext(dispatcherIO) {
            data = when (val result = ranichApi.getPersonalContacts(userId).toResult()) {
                is ApiResult.Success -> ContactData(contacts = result.data.map { it.toDomainModel() })
                is ApiResult.Error -> ContactData(contacts = emptyList())
            }
        }
        return data
    }

    override suspend fun getHospitalContact(): ContactData {
        val data: ContactData
        withContext(dispatcherIO) {
            data = when (val result = ranichApi.getHospitalContact(userId).toResult()) {
                is ApiResult.Success -> ContactData(contacts = result.data.map { it.toDomainModel() })
                is ApiResult.Error -> ContactData(contacts = emptyList())
            }
        }
        return data
    }

    override suspend fun getLogs(): ContactData {
        val data: ContactData
        withContext(dispatcherIO) {
            data = when (val result = ranichApi.getNotifyLogs(userId).toResult()) {
                is ApiResult.Success -> ContactData(contacts = result.data.map { it.toDomainModel() })
                is ApiResult.Error -> ContactData(contacts = emptyList())
            }
        }
        return data
    }

    override suspend fun postPersonalContact(contact: PostContactModel) {
        withContext(dispatcherIO) {
            ranichApi.postPersonalContacts(contact)
        }
    }

    override suspend fun postHospitalContact(contact: PostHospitalContactModel) {
        withContext(dispatcherIO) {
            ranichApi.postHospitalContacts(contact)
        }
    }

}