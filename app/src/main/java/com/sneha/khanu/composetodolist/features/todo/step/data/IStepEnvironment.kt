package com.sneha.khanu.composetodolist.features.todo.step.data

import com.wisnu.khanu.composetodolist.foundation.wrapper.DateTimeProvider
import com.wisnu.khanu.composetodolist.foundation.wrapper.IdProvider
import com.wisnu.khanu.composetodolist.model.ToDoColor
import com.wisnu.khanu.composetodolist.model.ToDoRepeat
import com.wisnu.khanu.composetodolist.model.ToDoStep
import com.wisnu.khanu.composetodolist.model.ToDoTask
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

interface IStepEnvironment {
    val idProvider: IdProvider
    val dateTimeProvider: DateTimeProvider
    fun getTask(taskId: String, listId: String): Flow<Pair<ToDoTask, ToDoColor>>
    suspend fun deleteTask(task: ToDoTask)
    suspend fun toggleTaskStatus(task: ToDoTask)
    suspend fun setRepeatTask(task: ToDoTask, toDoRepeat: ToDoRepeat)
    suspend fun toggleStepStatus(step: ToDoStep)
    suspend fun createStep(name: String, taskId: String)
    suspend fun deleteStep(step: ToDoStep)
    suspend fun updateTask(name: String, taskId: String)
    suspend fun updateStep(name: String, stepId: String)
    suspend fun updateTaskDueDate(
        date: LocalDateTime,
        isDueDateTimeSet: Boolean,
        taskId: String
    )

    suspend fun updateTaskNote(note: String, taskId: String)
    suspend fun resetTaskDueDate(taskId: String)
    suspend fun resetTaskTime(date: LocalDateTime, taskId: String)
}
