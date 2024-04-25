package com.example.ranich.data.network

import com.example.ranich.data.model.FallingAlertModel
import com.example.ranich.data.model.HospitalContactModel
import com.example.ranich.data.model.LoginModel
import com.example.ranich.data.model.LogsModel
import com.example.ranich.data.model.PersonalContactModel
import com.example.ranich.data.model.PostContactModel
import com.example.ranich.data.model.PostFallingAlertModel
import com.example.ranich.data.model.PostHospitalContactModel
import com.example.ranich.data.model.PostRegisterCloudMessage
import com.example.ranich.data.model.RegisterMessageResult
import com.example.ranich.data.model.UserProfileModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RanichApi {

    @POST("auth/login")
    suspend fun login(@Body loginModel: LoginModel): Response<UserProfileModel>

    @GET("users/{userId}")
    suspend fun getUserProfile(
        @Path("userId") userId: String,
    ): Response<UserProfileModel>

    @GET("personal-contacts/get-by-user-id/{userId}")
    suspend fun getPersonalContacts(
        @Path("userId") userId: String,
    ): Response<List<PersonalContactModel>>

    @GET("personal-contacts")
    suspend fun postPersonalContacts(
        @Body contact: PostContactModel,
    ): Response<Unit>

    @GET("hospital-contacts/get-by-user-id/{userId}")
    suspend fun getHospitalContact(
        @Path("userId") userId: String,
    ): Response<List<HospitalContactModel>>

    @GET("hospital-contacts")
    suspend fun postHospitalContacts(
        @Body contact: PostHospitalContactModel,
    ): Response<Unit>

    @GET("logs/get-by-user-id/{userId}")
    suspend fun getNotifyLogs(
        @Path("userId") userId: String,
    ): Response<List<LogsModel>>

    @POST("alerts/send-alert")
    suspend fun postFallingAlert(
        @Body fallingAlert: PostFallingAlertModel,
    ): Response<FallingAlertModel>

    @POST("notifications/register")
    suspend fun postRegisterCloudMessage(
        @Body registerModel: PostRegisterCloudMessage,
    ): Response<RegisterMessageResult>
}

class RanichServicesClient(
    retrofit: Retrofit,
) : RanichApi {

    private val client by lazy { retrofit.create(RanichApi::class.java) }

    override suspend fun login(loginModel: LoginModel): Response<UserProfileModel> {
        return client.login(loginModel)
    }

    override suspend fun getUserProfile(userId: String): Response<UserProfileModel> {
        return client.getUserProfile(userId)
    }

    override suspend fun getPersonalContacts(userId: String): Response<List<PersonalContactModel>> {
        return client.getPersonalContacts(userId)
    }

    override suspend fun postPersonalContacts(contact: PostContactModel): Response<Unit> {
        return client.postPersonalContacts(contact)
    }

    override suspend fun getHospitalContact(userId: String): Response<List<HospitalContactModel>> {
        return client.getHospitalContact(userId)
    }

    override suspend fun postHospitalContacts(contact: PostHospitalContactModel): Response<Unit> {
        return client.postHospitalContacts(contact)
    }

    override suspend fun getNotifyLogs(userId: String): Response<List<LogsModel>> {
        return client.getNotifyLogs(userId)
    }

    override suspend fun postFallingAlert(fallingAlert: PostFallingAlertModel): Response<FallingAlertModel> {
        return client.postFallingAlert(fallingAlert)
    }

    override suspend fun postRegisterCloudMessage(registerModel: PostRegisterCloudMessage): Response<RegisterMessageResult> {
        return client.postRegisterCloudMessage(registerModel)
    }
}
