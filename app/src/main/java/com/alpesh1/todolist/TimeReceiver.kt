package com.alpesh1.todolist

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.alpesh1.todolist.ModelClass.TaskModel

class TimeReceiver:BroadcastReceiver() {
    @SuppressLint("UnspecifiedImmutableFlag", "MissingPermission")
    override fun onReceive(context: Context?, intent: Intent?) {

        var i = Intent(context,MainActivity::class.java)
        val viewModel: TaskModel
        viewModel.showNotification(context)
         val alarmManager = context?.getSystemService(ALARM_SERVICE) as AlarmManager
        val alarmPendingIntent by lazy {
            val intent = Intent(context, AlarmReceiver::class.java)
            PendingIntent.getBroadcast(context, 0, intent, 0)
        }
        val HOUR_TO_SHOW_PUSH = viewModel.hourOfNotification.value
        val MINUTE_TO_SHOW_PUSH = viewModel.minuteOfNotification.value

    }
}