package com.sneha.khanu.composetodolist.features.localized.setting.ui

import com.wisnu.khanu.composetodolist.model.Language

sealed class LocalizedEffect {
    data class ApplyLanguage(val language: Language) : LocalizedEffect()
}
