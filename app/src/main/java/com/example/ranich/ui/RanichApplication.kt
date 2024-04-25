package com.example.ranich.ui

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.app.NotificationManager
import android.content.Context
import com.example.ranich.R
import com.example.ranich.di.networkModule
import com.example.ranich.di.repositoryModule
import com.example.ranich.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class RanichApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        initNotificationChannel(this)

        startKoin {
            androidContext(this@RanichApplication)
            androidLogger(level = Level.ERROR)
            modules(listOf(networkModule, repositoryModule, viewModelModule))
        }
    }

    private fun initNotificationChannel(context: Context) {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val groupId = "Emergency"
        val groupName = "Emergency"
        val channelGroup = NotificationChannelGroup(groupId, groupName)

        val channelId = "Ranich_Notification"
        val channelName = getString(R.string.app_name)
        val descriptionText = getString(R.string.app_name_description)
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(channelId, channelName, importance).apply {
            description = descriptionText
        }

        manager.createNotificationChannelGroup(channelGroup)
        manager.createNotificationChannel(channel)
    }
}