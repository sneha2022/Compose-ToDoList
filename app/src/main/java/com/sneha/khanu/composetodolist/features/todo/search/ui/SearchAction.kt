package com.sneha.khanu.composetodolist.features.todo.search.ui

import androidx.compose.ui.text.input.TextFieldValue
import com.wisnu.khanu.composetodolist.model.ToDoTask

sealed class SearchAction {
    data class ChangeSearchText(val text: TextFieldValue) : SearchAction()
    sealed class TaskAction : SearchAction() {
        data class Delete(val task: ToDoTask) : TaskAction()
        data class OnToggleStatus(val task: ToDoTask) : TaskAction()
    }
}
