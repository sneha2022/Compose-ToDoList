package com.sneha.khanu.composetodolist.foundation.datasource.preference.mapper

import com.wisnu.khanu.composetodolist.foundation.datasource.preference.model.LanguagePreference
import com.wisnu.khanu.composetodolist.model.Language

fun Language.toLanguagePreference(): LanguagePreference {
    return when (this) {
        Language.ENGLISH -> LanguagePreference.ENGLISH
        Language.INDONESIA -> LanguagePreference.INDONESIA
    }
}

fun LanguagePreference.toLanguage(): Language {
    return when (this) {
        LanguagePreference.ENGLISH -> Language.ENGLISH
        LanguagePreference.INDONESIA -> Language.INDONESIA
        LanguagePreference.UNRECOGNIZED -> Language.ENGLISH
    }
}
