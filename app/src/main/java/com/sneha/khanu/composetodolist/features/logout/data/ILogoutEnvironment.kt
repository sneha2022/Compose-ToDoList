package com.sneha.khanu.composetodolist.features.logout.data

import com.wisnu.khanu.composetodolist.model.User
import kotlinx.coroutines.flow.Flow

interface ILogoutEnvironment {
    suspend fun logout()
    fun getUser(): Flow<User>
}
