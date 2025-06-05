package com.sneha.khanu.composetodolist.features.host.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wisnu.khanu.composetodolist.foundation.theme.Theme

@Composable
fun Host(content: @Composable () -> Unit) {
    val viewModel = hiltViewModel<HostViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    Theme(theme = state.theme, content = content)
}
