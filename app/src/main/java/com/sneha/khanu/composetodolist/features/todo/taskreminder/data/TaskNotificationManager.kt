package com.sneha.khanu.composetodolist.features.todo.taskreminder.data


import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.NotificationCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.net.toUri
import com.wisnu.foundation.coredatetime.toMillis
import com.wisnu.khanu.composetodolist.R
import com.wisnu.khanu.composetodolist.features.todo.taskreminder.ui.TaskBroadcastReceiver
import com.wisnu.khanu.composetodolist.foundation.extension.ellipsisAt
import com.wisnu.khanu.composetodolist.foundation.localization.LocalizationUtil
import com.wisnu.khanu.composetodolist.foundation.theme.LightError
import com.wisnu.khanu.composetodolist.foundation.uicomponent.itemInfoDisplayable
import com.wisnu.khanu.composetodolist.model.ToDoList
import com.wisnu.khanu.composetodolist.model.ToDoTask
import com.wisnu.khanu.composetodolist.runtime.navigation.StepFlow
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskNotificationManager @Inject constructor(@ApplicationContext private val context: Context) {

    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager

    init {
        initChannel()
    }

    private fun initChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getLocalizedContext().getString(R.string.todo_task_channel_name)
            val description = getLocalizedContext().getString(R.string.todo_task_channel_description)
            val importance = NotificationManager.IMPORTANCE_HIGH

            NotificationChannel(CHANNEL_ID, name, importance).apply {
                this.description = description
                enableLights(true)
                lightColor = ResourcesCompat.getColor(getLocalizedContext().resources, R.color.light_primary, null)
                enableVibration(true)
                notificationManager?.createNotificationChannel(this)
            }
        }
    }

    fun show(task: ToDoTask, toDoList: ToDoList) {
        val builder = buildNotification(task, toDoList)
        val id = task.createdAt.toMillis().toInt()
        notificationManager?.notify(
            id,
            builder.build()
        )
    }

    fun dismiss(task: ToDoTask) {
        val id = task.createdAt.toMillis().toInt()
        notificationManager?.cancel(id)
    }

    private fun buildNotification(task: ToDoTask, toDoList: ToDoList): NotificationCompat.Builder {

        return NotificationCompat.Builder(context, CHANNEL_ID).apply {
            setSmallIcon(R.drawable.ic_round_check_24)
            setContentTitle(toDoList.name)
            setContentText(task.name)
            setStyle(NotificationCompat.BigTextStyle().bigText(task.name.ellipsisAt(100) + "\n" + task.itemInfoDisplayable(getLocalizedContext().resources, LightError)))
            setColor(ResourcesCompat.getColor(getLocalizedContext().resources, R.color.light_primary, null))
            setContentIntent(buildPendingIntent(task.id, toDoList.id))
            setAutoCancel(true)
            setColorized(true)
            setShowWhen(false)
            addAction(getSnoozeAction(task.id))
            addAction(getCompleteAction(task.id))
        }
    }


    private fun buildPendingIntent(taskId: String, listId: String): PendingIntent {
        val openTaskIntent = Intent(
            Intent.ACTION_VIEW,
            StepFlow.TaskDetailScreen.deeplink(taskId, listId).toUri()
        )
        val flags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }

        return TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(openTaskIntent)
            getPendingIntent(REQUEST_CODE_OPEN_TASK, flags)
        }
    }

    private fun getCompleteAction(taskId: String): NotificationCompat.Action {
        val actionTitle = getLocalizedContext().getString(R.string.todo_done)
        val intent = getIntent(taskId, TaskBroadcastReceiver.ACTION_NOTIFICATION_COMPLETED, REQUEST_CODE_ACTION_COMPLETE)
        return NotificationCompat.Action(ACTION_NO_ICON, actionTitle, intent)
    }

    private fun getSnoozeAction(taskId: String): NotificationCompat.Action {
        val actionTitle = getLocalizedContext().getString(R.string.todo_task_notification_action_snooze)
        val intent = getIntent(taskId, TaskBroadcastReceiver.ACTION_NOTIFICATION_SNOOZE, REQUEST_CODE_ACTION_SNOOZE)
        return NotificationCompat.Action(ACTION_NO_ICON, actionTitle, intent)
    }

    private fun getIntent(
        taskId: String,
        intentAction: String,
        requestCode: Int
    ): PendingIntent {
        val receiverIntent = Intent(context, TaskBroadcastReceiver::class.java).apply {
            action = intentAction
            putExtra(TaskBroadcastReceiver.EXTRA_TASK_ID, taskId)
        }

        return PendingIntent
            .getBroadcast(
                context,
                requestCode,
                receiverIntent,
                PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
    }

    private fun getLocalizedContext(): Context {
        val locale = AppCompatDelegate.getApplicationLocales().get(0) ?: Locale.getDefault()
        return LocalizationUtil.applyLanguageContext(context, locale)
    }

    companion object {
        private const val REQUEST_CODE_OPEN_TASK = 1
        private const val REQUEST_CODE_ACTION_COMPLETE = 2
        private const val REQUEST_CODE_ACTION_SNOOZE = 3
        private const val ACTION_NO_ICON = 0

        private const val CHANNEL_ID = "task_notification_channel"
    }
}
