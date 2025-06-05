package com.sneha.khanu.composetodolist.features.logout.ui

import androidx.compose.runtime.Immutable
import com.wisnu.khanu.composetodolist.model.User

@Immutable
data class LogoutState(val user: User = User())
