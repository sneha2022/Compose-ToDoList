package com.sneha.khanu.composetodolist.features.todo.search.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wisnu.khanu.composetodolist.R
import com.wisnu.khanu.composetodolist.features.todo.all.ui.ItemAllState
import com.wisnu.khanu.composetodolist.features.todo.all.ui.TaskContent
import com.wisnu.khanu.composetodolist.foundation.uicomponent.PgIcon
import com.wisnu.khanu.composetodolist.foundation.uicomponent.PgPageLayout
import com.wisnu.khanu.composetodolist.foundation.uicomponent.PgTextField
import com.wisnu.khanu.composetodolist.model.ToDoTask

@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    onClickBack: () -> Unit,
    onTaskItemClick: (String, String) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SearchScreen(
        searchText = state.searchText,
        items = state.items,
        onClickBack = onClickBack,
        onSearchChange = { viewModel.dispatch(SearchAction.ChangeSearchText(it)) },
        onTaskItemClick = { onTaskItemClick(it.task.id, it.list.id) },
        onTaskStatusItemClick = { viewModel.dispatch(SearchAction.TaskAction.OnToggleStatus(it)) },
        onTaskSwipeToDelete = { viewModel.dispatch(SearchAction.TaskAction.Delete(it)) },
    )
}

@Composable
private fun SearchScreen(
    searchText: TextFieldValue,
    items: List<ItemAllState>,
    onClickBack: () -> Unit,
    onSearchChange: (TextFieldValue) -> Unit,
    onTaskItemClick: (ItemAllState.Task) -> Unit,
    onTaskStatusItemClick: (ToDoTask) -> Unit,
    onTaskSwipeToDelete: (ToDoTask) -> Unit
) {
    val focusRequest = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequest.requestFocus()
    }

    PgPageLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp)
        ) {
            SearchWidget(
                modifier = Modifier
                    .weight(1f),
                searchText = searchText,
                focusRequester = focusRequest,
                onSearchChange = onSearchChange,
                onCancelClick = onClickBack
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1F)
        ) {
            SearchContent(
                items = items,
                onTaskItemClick = onTaskItemClick,
                onTaskStatusItemClick = onTaskStatusItemClick,
                onTaskSwipeToDelete = onTaskSwipeToDelete,
            )
        }
    }
}

@Composable
fun SearchWidget(
    modifier: Modifier,
    searchText: TextFieldValue,
    focusRequester: FocusRequester,
    onSearchChange: (TextFieldValue) -> Unit,
    onCancelClick: () -> Unit,
) {
    PgTextField(
        value = searchText,
        onValueChange = onSearchChange,
        placeholderValue = stringResource(R.string.todo_search),
        modifier = modifier
            .height(50.dp)
            .focusRequester(focusRequester),
        shape = MaterialTheme.shapes.large,
        textStyle = MaterialTheme.typography.titleSmall,
        leadingIcon = {
            PgIcon(
                imageVector = Icons.Rounded.Search
            )
        }
    )

    Spacer(Modifier.width(8.dp))

    Column {
        TextButton(
            onClick = onCancelClick,
            shape = CircleShape,
            colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.onSurface)
        ) {
            Text(
                text = stringResource(R.string.todo_cancel),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Spacer(Modifier.height(2.dp))
    }
}

@Composable
fun SearchContent(
    items: List<ItemAllState>,
    onTaskItemClick: (ItemAllState.Task) -> Unit,
    onTaskStatusItemClick: (ToDoTask) -> Unit,
    onTaskSwipeToDelete: (ToDoTask) -> Unit
) {
    TaskContent(
        items,
        onTaskItemClick,
        onTaskStatusItemClick,
        onTaskSwipeToDelete
    )
}
