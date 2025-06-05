package com.sneha.khanu.composetodolist.features.todo.taskreminder.data

import com.wisnu.khanu.composetodolist.model.TaskWithList
import com.wisnu.khanu.composetodolist.model.ToDoTask
import kotlinx.coroutines.flow.Flow

interface ITaskReminderEnvironment {
    fun notifyNotification(taskId: String): Flow<TaskWithList>
    fun snoozeReminder(taskId: String): Flow<TaskWithList>
    suspend fun completeReminder(taskId: String): Flow<TaskWithList>
    fun restartAllReminder(): Flow<List<ToDoTask>>
}
