package com.sneha.khanu.composetodolist.foundation.extension

import com.wisnu.khanu.composetodolist.foundation.datasource.preference.mapper.toTheme
import com.sneha.khanu.composetodolist.foundation.datasource.preference.model.ThemePreference
import com.wisnu.khanu.composetodolist.model.Theme
import org.junit.Assert
import org.junit.Test

class ThemePreferenceExtTest {

    @Test
    fun mapToTheme() {
        Assert.assertEquals(Theme.SYSTEM, com.sneha.khanu.composetodolist.foundation.datasource.preference.model.ThemePreference.SYSTEM.toTheme())
        Assert.assertEquals(Theme.LIGHT, com.sneha.khanu.composetodolist.foundation.datasource.preference.model.ThemePreference.LIGHT.toTheme())
        Assert.assertEquals(Theme.NIGHT, com.sneha.khanu.composetodolist.foundation.datasource.preference.model.ThemePreference.NIGHT.toTheme())
        Assert.assertEquals(Theme.TWILIGHT, com.sneha.khanu.composetodolist.foundation.datasource.preference.model.ThemePreference.TWILIGHT.toTheme())
        Assert.assertEquals(Theme.AURORA, com.sneha.khanu.composetodolist.foundation.datasource.preference.model.ThemePreference.AURORA.toTheme())
        Assert.assertEquals(Theme.SUNRISE, com.sneha.khanu.composetodolist.foundation.datasource.preference.model.ThemePreference.SUNRISE.toTheme())
    }

}
