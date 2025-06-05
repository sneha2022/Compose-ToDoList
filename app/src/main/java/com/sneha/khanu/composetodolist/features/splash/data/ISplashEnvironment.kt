package com.sneha.khanu.composetodolist.features.splash.data

import com.wisnu.khanu.composetodolist.model.Credential
import kotlinx.coroutines.flow.Flow

interface ISplashEnvironment {
    fun getCredential(): Flow<Credential>
}
