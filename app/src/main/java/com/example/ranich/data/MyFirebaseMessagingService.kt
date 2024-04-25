package com.example.ranich.data

import android.util.Log
import com.example.ranich.common.NotificationMaster
import com.example.ranich.common.SessionManager
import com.example.ranich.data.model.PostRegisterCloudMessage
import com.example.ranich.data.network.RanichServicesClient
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinComponent

class MyFirebaseMessagingService : FirebaseMessagingService(), KoinComponent {

    private val sessionManager: SessionManager by inject()
    private val client: RanichServicesClient by inject()
    private val notification: NotificationMaster by inject()
    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        message.notification?.let {
            Log.e("FCM", "get message ${it.body}")

            notification.callNotification(it.title ?: "", it.body ?: "")
        }
    }

    override fun onNewToken(token: String) {
        sendRegistrationToServer(token)
    }

    private fun sendRegistrationToServer(token: String? = null) {
        if (token == null) {
            FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    return@OnCompleteListener
                }

                val registerToken = task.result
                sessionManager.saveCloudMessageUUID(registerToken)
                postRegistrationToServer(registerToken)
            })
        } else {
            postRegistrationToServer(token)
            sessionManager.saveCloudMessageUUID(token)
        }
    }

    private fun postRegistrationToServer(token: String) {
        val registerMessagingServiceJob = scope.launch {
            val userID = sessionManager.getUserUUID()
            val body = PostRegisterCloudMessage(
                firebaseToken = token,
                userId = userID
            )
            client.postRegisterCloudMessage(body)
        }
        job[registerMessagingServiceJob.key]
        job.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}