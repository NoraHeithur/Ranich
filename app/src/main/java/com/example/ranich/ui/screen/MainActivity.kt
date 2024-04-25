package com.example.ranich.ui.screen

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.example.ranich.common.SessionManager
import com.example.ranich.domain.common.SensorObserver
import com.example.ranich.ui.RanichMainScreen
import com.example.ranich.ui.theme.FailAwareTheme
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.ktx.messaging
import com.markodevcic.peko.PermissionRequester
import com.markodevcic.peko.PermissionResult
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val sensorObserver: SensorObserver by inject()
    private val sessionManager: SessionManager by inject()
    private val permissionRequester: PermissionRequester by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initPermission()

        initFCMToken()

        setContent {

            val rememberUserSession = sessionManager.getUserUUID()

            FailAwareTheme {
                RanichMainScreen(
                    userSession = rememberUserSession
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        sensorObserver.startSensor()
    }

    override fun onPause() {
        super.onPause()
        sensorObserver.stopSensor()
    }

    override fun onDestroy() {
        super.onDestroy()
        sensorObserver.stopSensor()
    }

    private fun initPermission() {
        lifecycleScope.launch {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                permissionRequester.request(Manifest.permission.POST_NOTIFICATIONS).collect {
                    setPermissionResult(it)
                }
            }
        }
    }

    private fun setPermissionResult(result: PermissionResult) {
        when (result) {
            is PermissionResult.Denied.NeedsRationale -> {
                PermissionRequester.initialize(this.applicationContext)
            }
            else -> {}

        }
    }

    private fun initFCMToken() {
        val token = sessionManager.getCloudMessageUUID()
        if (token.isEmpty()) {
            FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    return@OnCompleteListener
                }
                val registerToken = task.result
                sessionManager.saveCloudMessageUUID(registerToken)
            })
        }
        Firebase.messaging.isAutoInitEnabled = true
    }
}