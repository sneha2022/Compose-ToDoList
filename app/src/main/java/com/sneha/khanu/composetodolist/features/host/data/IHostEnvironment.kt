package com.sneha.khanu.composetodolist.features.host.data

import com.wisnu.khanu.composetodolist.model.Theme
import kotlinx.coroutines.flow.Flow

interface IHostEnvironment {
    fun getTheme(): Flow<Theme>
}
