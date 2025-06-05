package com.sneha.khanu.composetodolist.features.dashboard.ui

import androidx.compose.runtime.Immutable
import com.wisnu.khanu.composetodolist.model.User

@Immutable
data class DashboardState(val user: User = User())
