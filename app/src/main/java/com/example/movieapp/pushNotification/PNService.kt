package com.example.movieapp.pushNotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import androidx.core.net.toUri
import com.example.movieapp.Constants
import com.example.movieapp.Constants.DEEPLINK_HOME_HOST
import com.example.movieapp.Constants.DEEPLINK_SCHEME
import com.example.movieapp.Constants.PN_CHANNEL_ID
import com.example.movieapp.MainActivity
import com.example.movieapp.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class PNService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCM", "New Token: $token")
        // Send the token to your server if needed
    }
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val title = message.notification?.title ?: "Default Title"
        val body = message.notification?.body ?: "Default body"

        Log.d("FCM", "Message Received: $title - $body")
        showNotification(title,body)
    }

    private fun showNotification(title : String, body : String) {
        val activityIntent = Intent(this, MainActivity::class.java).apply {
            data = "${DEEPLINK_SCHEME}://${DEEPLINK_HOME_HOST}/23".toUri()
        }
        val pendingIntent = TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(activityIntent)
            getPendingIntent(0, PendingIntent.FLAG_IMMUTABLE)
        }
        val channelId = ContextCompat.getString(this,R.string.default_notification_channel_id)
        val notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(body)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        val notificationManager = getSystemService<NotificationManager>()!!
        notificationManager.notify(1, notification)
    }
}