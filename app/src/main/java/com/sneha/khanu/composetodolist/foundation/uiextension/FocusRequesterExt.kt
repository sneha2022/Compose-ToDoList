package com.sneha.khanu.composetodolist.foundation.uiextension

import androidx.compose.ui.focus.FocusRequester
import kotlinx.coroutines.delay

suspend fun FocusRequester.requestFocusImeAware() {
    delay(260)
    requestFocus()
}
