package com.yatochk.pillapp.model

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import com.yatochk.pillapp.R


class NotifyReceiver : BroadcastReceiver() {

    companion object {
        const val NOTIFY_CHANEL = "pill_notification"
    }

    private lateinit var notificationManager: NotificationManager

    override fun onReceive(p0: Context?, p1: Intent?) {
        if (p1 == null || p0 == null) return
        p1.getStringExtra(NotifyService.MEDICATION_NAME)?.also {
            notificationManager = p0.getSystemService(LifecycleService.NOTIFICATION_SERVICE) as NotificationManager
            createNotifyChanel()
            val id = p1.getIntExtra(NotifyService.MEDICATION_ID, 1)
            sendNotify(p0, id, it, p0.getString(R.string.notify_msg))
        }
    }

    private fun createNotifyChanel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIFY_CHANEL,
                "PillRemind",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotify(context: Context, id: Int, name: String, text: String) {
        val notificationBuilder = NotificationCompat.Builder(context, "default")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(name)
            .setContentText(text)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationBuilder.setChannelId(NOTIFY_CHANEL)
        }
        notificationManager.notify(id, notificationBuilder.build())
    }

}