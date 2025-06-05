package com.sneha.khanu.composetodolist.foundation.datasource.preference.mapper

import com.sneha.khanu.composetodolist.foundation.datasource.preference.model.ThemePreference
import com.wisnu.khanu.composetodolist.model.Theme

fun Theme.toThemePreference() = when (this) {
    Theme.SYSTEM -> com.sneha.khanu.composetodolist.foundation.datasource.preference.model.ThemePreference.SYSTEM
    Theme.LIGHT -> com.sneha.khanu.composetodolist.foundation.datasource.preference.model.ThemePreference.LIGHT
    Theme.TWILIGHT -> com.sneha.khanu.composetodolist.foundation.datasource.preference.model.ThemePreference.TWILIGHT
    Theme.NIGHT -> com.sneha.khanu.composetodolist.foundation.datasource.preference.model.ThemePreference.NIGHT
    Theme.SUNRISE -> com.sneha.khanu.composetodolist.foundation.datasource.preference.model.ThemePreference.SUNRISE
    Theme.AURORA -> com.sneha.khanu.composetodolist.foundation.datasource.preference.model.ThemePreference.AURORA
    Theme.WALLPAPER -> com.sneha.khanu.composetodolist.foundation.datasource.preference.model.ThemePreference.WALLPAPER
}

fun com.sneha.khanu.composetodolist.foundation.datasource.preference.model.ThemePreference.toTheme() = when (this) {
    com.sneha.khanu.composetodolist.foundation.datasource.preference.model.ThemePreference.SYSTEM -> Theme.SYSTEM
    com.sneha.khanu.composetodolist.foundation.datasource.preference.model.ThemePreference.LIGHT -> Theme.LIGHT
    com.sneha.khanu.composetodolist.foundation.datasource.preference.model.ThemePreference.TWILIGHT -> Theme.TWILIGHT
    com.sneha.khanu.composetodolist.foundation.datasource.preference.model.ThemePreference.NIGHT -> Theme.NIGHT
    com.sneha.khanu.composetodolist.foundation.datasource.preference.model.ThemePreference.SUNRISE -> Theme.SUNRISE
    com.sneha.khanu.composetodolist.foundation.datasource.preference.model.ThemePreference.AURORA -> Theme.AURORA
    com.sneha.khanu.composetodolist.foundation.datasource.preference.model.ThemePreference.WALLPAPER -> Theme.WALLPAPER
    com.sneha.khanu.composetodolist.foundation.datasource.preference.model.ThemePreference.UNRECOGNIZED -> Theme.SYSTEM
}
