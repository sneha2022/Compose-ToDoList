package com.sneha.khanu.composetodolist.features.logout.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wisnu.khanu.composetodolist.R
import com.wisnu.khanu.composetodolist.foundation.uicomponent.PgButton
import com.wisnu.khanu.composetodolist.foundation.uicomponent.PgModalBackHeader
import com.wisnu.khanu.composetodolist.foundation.uicomponent.PgModalLayout
import com.wisnu.khanu.composetodolist.foundation.uicomponent.Profile
import com.wisnu.khanu.composetodolist.foundation.viewmodel.HandleEffect

@Composable
fun LogoutScreen(
    viewModel: LogoutViewModel,
    onClickBack: () -> Unit,
    onNavigateToSplash: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HandleEffect(viewModel) {
        when (it) {
            LogoutEffect.NavigateToSplash -> {
                onNavigateToSplash()
            }
        }
    }

    PgModalLayout(
        title = {
            PgModalBackHeader(
                text = stringResource(R.string.setting_logout_confirm),
                onClickBack = onClickBack
            )
        },
        content = {
            item {
                Profile(state.user.email, modifier = Modifier.padding(bottom = 8.dp))
            }

            item {
                PgButton(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    onClick = { viewModel.dispatch(LogoutAction.ClickLogout) }
                ) { Text(text = stringResource(R.string.setting_logout), color = Color.White) }
            }
        }
    )
}
