package com.example.ranich.domain.repository

import com.example.ranich.common.ApiResult
import com.example.ranich.common.toResult
import com.example.ranich.data.mapper.toDomainModel
import com.example.ranich.data.network.RanichServicesClient
import com.example.ranich.di.DispatcherIO
import com.example.ranich.domain.model.UserProfile
import kotlinx.coroutines.withContext

interface UserRepository {
    suspend fun getUserInfo(userId: String): UserProfile
}

class UserRepositoryImpl(
    private val ranichApi: RanichServicesClient,
    private val dispatcherIO: DispatcherIO
): UserRepository {

    override suspend fun getUserInfo(userId: String): UserProfile {
        val data: UserProfile
        withContext(dispatcherIO) {
            data = when (val result = ranichApi.getUserProfile(userId).toResult()) {
                is ApiResult.Success -> result.data.toDomainModel()
                is ApiResult.Error -> throw result.exception?.cause!!
            }
        }
        return data
    }
}