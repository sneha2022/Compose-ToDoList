package com.sneha.khanu.composetodolist.features.dashboard.data

import com.wisnu.khanu.composetodolist.model.ToDoTaskDiff
import com.wisnu.khanu.composetodolist.model.User
import kotlinx.coroutines.flow.Flow

interface IDashboardEnvironment {
    fun getUser(): Flow<User>
    fun listenToDoTaskDiff(): Flow<ToDoTaskDiff>
}
