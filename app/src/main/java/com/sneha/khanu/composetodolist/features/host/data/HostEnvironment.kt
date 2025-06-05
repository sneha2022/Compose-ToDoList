package com.sneha.khanu.composetodolist.features.host.data

import com.wisnu.khanu.composetodolist.foundation.datasource.preference.provider.ThemeProvider
import com.wisnu.khanu.composetodolist.model.Theme
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HostEnvironment @Inject constructor(
    private val themeProvider: ThemeProvider
) : IHostEnvironment {

    override fun getTheme(): Flow<Theme> {
        return themeProvider.getTheme()
    }

}
