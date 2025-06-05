package com.sneha.khanu.composetodolist.foundation.extension

import com.wisnu.khanu.composetodolist.model.Credential

fun Credential.isLoggedIn() = token.isNotBlank()
