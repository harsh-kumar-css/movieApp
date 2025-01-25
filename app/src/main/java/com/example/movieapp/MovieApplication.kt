package com.example.movieapp

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import androidx.core.net.toUri
import com.example.movieapp.Constants.DEEPLINK_HOME_HOST
import com.example.movieapp.Constants.DEEPLINK_SCHEME
import com.example.movieapp.Constants.PN_CHANNEL_ID
import com.example.movieapp.Constants.PN_CHANNEL_NAME
import com.example.movieapp.pushNotification.PNService
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MovieApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        val channelId = ContextCompat.getString(this, R.string.default_notification_channel_id)
        val channelName = ContextCompat.getString(this, R.string.default_notification_channel_name)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                channelName, NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager = getSystemService<NotificationManager>()!!
            notificationManager.createNotificationChannel(channel)
        }
    }
}