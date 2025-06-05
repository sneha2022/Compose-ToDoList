package com.sneha.khanu.composetodolist.foundation.extension

import com.wisnu.khanu.composetodolist.foundation.datasource.preference.mapper.toThemePreference
import com.sneha.khanu.composetodolist.foundation.datasource.preference.model.ThemePreference
import com.wisnu.khanu.composetodolist.model.Theme
import org.junit.Assert
import org.junit.Test

class ThemeExtTest {
    @Test
    fun mapToPreference() {
        Assert.assertEquals(com.sneha.khanu.composetodolist.foundation.datasource.preference.model.ThemePreference.SYSTEM, Theme.SYSTEM.toThemePreference())
        Assert.assertEquals(com.sneha.khanu.composetodolist.foundation.datasource.preference.model.ThemePreference.LIGHT, Theme.LIGHT.toThemePreference())
        Assert.assertEquals(com.sneha.khanu.composetodolist.foundation.datasource.preference.model.ThemePreference.NIGHT, Theme.NIGHT.toThemePreference())
        Assert.assertEquals(com.sneha.khanu.composetodolist.foundation.datasource.preference.model.ThemePreference.AURORA, Theme.AURORA.toThemePreference())
        Assert.assertEquals(com.sneha.khanu.composetodolist.foundation.datasource.preference.model.ThemePreference.TWILIGHT, Theme.TWILIGHT.toThemePreference())
        Assert.assertEquals(com.sneha.khanu.composetodolist.foundation.datasource.preference.model.ThemePreference.SUNRISE, Theme.SUNRISE.toThemePreference())
    }
}
