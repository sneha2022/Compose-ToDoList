package com.sneha.khanu.composetodolist.features.todo.grouplist.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wisnu.khanu.composetodolist.R
import com.wisnu.khanu.composetodolist.foundation.extension.toColor
import com.sneha.khanu.composetodolist.foundation.theme.DividerAlpha
import com.wisnu.khanu.composetodolist.foundation.uicomponent.PgButton
import com.wisnu.khanu.composetodolist.foundation.uicomponent.PgEmpty
import com.wisnu.khanu.composetodolist.foundation.uicomponent.PgIcon
import com.wisnu.khanu.composetodolist.foundation.uicomponent.PgIconButton
import com.wisnu.khanu.composetodolist.foundation.uicomponent.PgModalBackHeader
import com.wisnu.khanu.composetodolist.foundation.uicomponent.PgModalLayout
import com.wisnu.khanu.composetodolist.foundation.uicomponent.PgModalTitle
import com.wisnu.khanu.composetodolist.foundation.uicomponent.PgSecondaryButton
import com.wisnu.khanu.composetodolist.model.GroupIdWithList

@Composable
fun UpdateGroupListScreen(
    viewModel: UpdateGroupListViewModel,
    onSubmit: () -> Unit,
    onSkip: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    UpdateGroupListScreen(
        state = state,
        title = {
            PgModalTitle(
                text = stringResource(R.string.todo_update_group_list)
            )
        },
        onItemChange = { viewModel.dispatch(UpdateGroupListAction.Change(it)) },
        onSubmit = {
            viewModel.dispatch(UpdateGroupListAction.Submit)
            onSubmit()
        },
        onSkip = onSkip
    )
}

@Composable
fun EditGroupListScreen(
    viewModel: UpdateGroupListViewModel,
    onClickBack: () -> Unit,
    onSubmit: () -> Unit,
    onSkip: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    UpdateGroupListScreen(
        state = state,
        title = {
            PgModalBackHeader(
                text = stringResource(R.string.todo_update_group_list),
                onClickBack = onClickBack
            )
        },
        onItemChange = { viewModel.dispatch(UpdateGroupListAction.Change(it)) },
        onSubmit = {
            viewModel.dispatch(UpdateGroupListAction.Submit)
            onSubmit()
        },
        onSkip = onSkip
    )
}

@Composable
private fun UpdateGroupListScreen(
    state: UpdateGroupListState,
    title: @Composable () -> Unit,
    onItemChange: (GroupIdWithList) -> Unit,
    onSubmit: () -> Unit,
    onSkip: () -> Unit
) {
    PgModalLayout(
        title = title,
        content = {
            if (state.items.isEmpty()) {
                item {
                    PgEmpty(
                        stringResource(R.string.todo_no_list),
                        Modifier.padding(bottom = 16.dp)
                    )
                }
            } else {
                items(state.items) {
                    Cell(
                        name = it.list.name,
                        color = it.list.color.toColor(),
                        selected = !it.isUngroup(),
                        onClick = { onItemChange(it) }
                    )

                    HorizontalDivider(
                        modifier = Modifier.padding(start = 48.dp),
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = com.sneha.khanu.composetodolist.foundation.theme.DividerAlpha)
                    )
                }
            }

            item {
                Row(modifier = Modifier.padding(16.dp)) {
                    PgSecondaryButton(
                        modifier = Modifier.weight(1F),
                        onClick = onSkip,
                    ) { Text(text = stringResource(R.string.todo_skip), color = MaterialTheme.colorScheme.onSecondary) }

                    Spacer(Modifier.width(16.dp))

                    PgButton(
                        modifier = Modifier.weight(1F),
                        onClick = onSubmit,
                        enabled = state.isChanges
                    ) { Text(text = stringResource(R.string.todo_add), color = Color.White) }
                }
            }
        }
    )
}

@Composable
private fun Cell(
    name: String,
    color: Color,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth(),
    ) {
        Spacer(Modifier.size(16.dp))
        Box(
            modifier = Modifier
                .size(28.dp)
                .background(shape = CircleShape, color = color),
            contentAlignment = Alignment.Center
        ) {
            PgIcon(
                imageVector = Icons.AutoMirrored.Rounded.List,
                modifier = Modifier
                    .size(20.dp)
            )
        }
        Spacer(Modifier.size(8.dp))
        Text(
            text = name,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
        Spacer(Modifier.size(8.dp))

        PgIconButton(
            onClick = onClick,
            color = Color.Transparent
        ) {
            PgIcon(
                imageVector = if (selected) {
                    Icons.Rounded.Check
                } else {
                    Icons.Rounded.Add
                }
            )
        }
        Spacer(Modifier.size(8.dp))
    }
}

