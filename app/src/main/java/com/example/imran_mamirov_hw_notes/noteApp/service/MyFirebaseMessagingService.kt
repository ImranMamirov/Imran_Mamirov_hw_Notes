package com.example.imran_mamirov_hw_notes.noteApp.service

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.imran_mamirov_hw_notes.R
import com.example.imran_mamirov_hw_notes.noteApp.ui.activity.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class MyFirebaseMessagingService : FirebaseMessagingService() {
    companion object {
        const val CHANNEL_ID = "notification_channel"
        const val CHANNEL_NAME = "Notification_channel"
        const val NOTIFICATION_ID = 0
        const val PERMISSION_REQUEST_CODE = 1001
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onMessageReceived(message: RemoteMessage) {
        message.notification?.let { notification ->
            val title = notification.title ?: ""
            val description = notification.body ?: ""
            showNotification(title, description)
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun showNotification(title: String, description: String) {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        val notificationLayout = getNotificationLayout(title, description)
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_stat_ic_notification)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomContentView(notificationLayout)

        createNotificationChannel()
        with(NotificationManagerCompat.from(this)) {
            if (ActivityCompat.checkSelfPermission(
                    this@MyFirebaseMessagingService,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                val builder = AlertDialog.Builder(this@MyFirebaseMessagingService)
                builder.setTitle("Notification Permission Required")
                builder.setMessage("This app needs notification permission to notify you about important updates.")
                builder.setPositiveButton("Ok") { dialog, _ ->
                    dialog.dismiss()
                }
                builder.setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                }
                builder.create().show()
            }
            notify(NOTIFICATION_ID, builder.build())
        }
    }

    private fun getNotificationLayout(title: String, description: String): RemoteViews {
        val remoteViews = RemoteViews(packageName, R.layout.item_notification)
        remoteViews.setTextViewText(R.id.tv_title, title)
        remoteViews.setTextViewText(R.id.tv_description, description)
        remoteViews.setImageViewResource(R.id.img_logo, R.drawable.ic_stat_ic_notification)
        return remoteViews
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel =
                NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }
    }
}