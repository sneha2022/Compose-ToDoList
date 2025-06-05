package com.sneha.khanu.composetodolist.features.localized.setting.data

import com.wisnu.khanu.composetodolist.model.Language
import kotlinx.coroutines.flow.Flow

interface ILocalizedSettingEnvironment {
    fun getLanguage(): Flow<Language>
    suspend fun setLanguage(language: Language)
}

