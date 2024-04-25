package com.example.ranich.common

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import org.koin.core.component.KoinComponent
import java.security.GeneralSecurityException

interface SessionManager {
    fun saveUserUUID(uuid: String)
    fun getUserUUID(): String
    fun saveEmergencyContactUUID(uuid: String)
    fun getEmergencyContactUUID(): String
    fun saveCloudMessageUUID(token: String)
    fun getCloudMessageUUID(): String
    fun clearPrefData()
}

class SessionManagerImpl(
    private val context: Context
): SessionManager, KoinComponent {

    companion object {
        private const val PREF_FILE_NAME = "Ranich_pref"

        const val USER_KEY_PREF = "user_key_pref"
        const val EMERGENCY_KEY_PREF = "emergency_key_pref"
        const val FCM_KEY_PREF = "fcm_key_pref"
    }

    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val pref: SharedPreferences = try {
        createSharedPreferences()
    } catch (exception: GeneralSecurityException) {
        clearPrefData()
        createSharedPreferences()
    }

    private fun createSharedPreferences() = EncryptedSharedPreferences.create(
        context,
        PREF_FILE_NAME,
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
    )

    override fun saveUserUUID(uuid: String) {
        pref.edit().putString(USER_KEY_PREF, uuid).apply()
    }

    override fun saveEmergencyContactUUID(uuid: String) {
        pref.edit().putString(EMERGENCY_KEY_PREF, uuid).apply()
    }

    override fun getUserUUID(): String {
        return pref.getString(USER_KEY_PREF, "") ?: ""
    }

    override fun getEmergencyContactUUID(): String {
        return pref.getString(EMERGENCY_KEY_PREF, "") ?: ""
    }

    override fun saveCloudMessageUUID(token: String) {
        pref.edit().putString(FCM_KEY_PREF, token).apply()
    }

    override fun getCloudMessageUUID(): String {
        return pref.getString(FCM_KEY_PREF, "") ?: ""
    }

    override fun clearPrefData() {
        pref.edit().clear().apply()
    }
}