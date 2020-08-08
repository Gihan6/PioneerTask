package com.qdreamcaller.creativemindstask.util.scheduler

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.qdreamcaller.creativemindstask.R
import com.qdreamcaller.creativemindstask.ui.main.view.MainActivity


class NotificationWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    override fun doWork(): Result {
        // Your work here.
        showNotification()
        // Your task result
        return Result.success()
    }

    private fun showNotification() {
        val manager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "task_channel"
        val channelName = "task_name"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }

        val contentIntent = PendingIntent.getActivity(
            applicationContext,
            0,
            Intent(applicationContext, MainActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT
        )


        val builder =
            NotificationCompat.Builder(applicationContext, channelId)
                .setContentTitle("Creative Minds")
                .setContentText("Refresh Data After One Hour")
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)




        manager.notify(1, builder.build())
    }

}