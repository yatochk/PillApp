package com.yatochk.pillapp.model

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
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
        p1.getStringExtra("name")?.also {
            notificationManager = p0.getSystemService(LifecycleService.NOTIFICATION_SERVICE) as NotificationManager
            sendNotify(p0, 3456788, it, "Пора принять")
        }
    }

    private fun sendNotify(context: Context, id: Int, name: String, text: String) {
        val notification = NotificationCompat.Builder(context, NOTIFY_CHANEL)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(name)
            .setContentText(text)
            .build()
        notificationManager.notify(id, notification)
    }

}