package com.udacity

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

object NotificationHelper {
    const val NOTIFICATION_CHANNEL_ID = "101"

    @RequiresApi(Build.VERSION_CODES.O)
            /*mohamed elgohary*/
    // Create Notification Channel Function
    fun createNotificationChannel(channelId: String, context: Context) {
        val notificationChannel =
            NotificationChannel(
                channelId,
                "Load App",
                NotificationManager.IMPORTANCE_DEFAULT
            )
        notificationChannel.apply {
            setShowBadge(true)
            description = "Loading Application Download Notification"
        }
        val notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(notificationChannel)
    }

    /*mohamed elgohary*/
    // Send Notification Channel Function
    fun sendNotification(context: Context, fileName: String, status: String) {
        val notificationIntent = Intent(context, DetailActivity::class.java).apply {
            flags =
                Intent.FLAG_ACTIVITY_CLEAR_TOP
            putExtra("FILE_NAME", fileName)
            putExtra("STATUS", status)
        }
        val pendingIntent = TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(notificationIntent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val notification = context.let {
            NotificationCompat.Builder(it, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.notifications)
                .setColor(Color.BLUE)
                .setContentTitle("Loading Application")
                .setContentText("Your Download Has been Completed")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build()
        }

        val notificationManagerCompat = NotificationManagerCompat.from(context)
        notificationManagerCompat.notify(1, notification)
    }
}
