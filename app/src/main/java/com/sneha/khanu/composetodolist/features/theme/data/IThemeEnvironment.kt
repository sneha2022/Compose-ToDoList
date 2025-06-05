package com.sneha.khanu.composetodolist.features.theme.data

import com.wisnu.khanu.composetodolist.model.Theme
import kotlinx.coroutines.flow.Flow

interface IThemeEnvironment {
    fun getTheme(): Flow<Theme>
    suspend fun setTheme(theme: Theme)
}
