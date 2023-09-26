package com.example.task1_android_components.service

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.task1_android_components.R
import com.example.task1_android_components.broadcast.MyBroadcastReceiver
import com.example.task1_android_components.utils.Constants

class RunningService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            Actions.START.toString() -> start()
            Actions.STOP.toString() -> stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun start() {
        val intent = Intent(this, MyBroadcastReceiver::class.java)
        intent.action = Constants.INTENT_ACTION
        val pendingIntent =
            PendingIntent.getBroadcast(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

        val notification = NotificationCompat.Builder(this, Constants.NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(getString(R.string.notification_title))
            .setContentText(getString(R.string.notification_text))
            .addAction(
                R.drawable.ic_launcher_foreground,
                getString(R.string.notification_action_title),
                pendingIntent
            )
            .build()
        startForeground(1, notification)
    }

    enum class Actions {
        START, STOP
    }
}