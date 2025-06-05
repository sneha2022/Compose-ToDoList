package com.sneha.khanu.composetodolist.features.login.ui

import androidx.compose.runtime.Immutable

@Immutable
data class LoginState(
    val email: String = "",
    val password: String = "",
    val showEmailInvalidError: Boolean = false
)
