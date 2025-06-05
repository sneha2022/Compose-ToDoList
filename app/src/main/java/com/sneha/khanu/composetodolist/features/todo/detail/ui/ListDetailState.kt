package com.sneha.khanu.composetodolist.features.todo.detail.ui

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import com.wisnu.khanu.composetodolist.foundation.theme.ListBlue
import com.wisnu.khanu.composetodolist.foundation.theme.ListBrown
import com.wisnu.khanu.composetodolist.foundation.theme.ListGreen
import com.wisnu.khanu.composetodolist.foundation.theme.ListOrange
import com.wisnu.khanu.composetodolist.foundation.theme.ListPurple
import com.wisnu.khanu.composetodolist.foundation.theme.ListRed
import com.wisnu.khanu.composetodolist.foundation.theme.ListYellow
import com.wisnu.khanu.composetodolist.foundation.wrapper.DateTimeProviderImpl
import com.wisnu.khanu.composetodolist.model.ToDoColor
import com.wisnu.khanu.composetodolist.model.ToDoList
import com.wisnu.khanu.composetodolist.model.ToDoTask

@Immutable
data class ListDetailState(
    val list: ToDoList = ToDoList(
        createdAt = DateTimeProviderImpl().now(),
        updatedAt = DateTimeProviderImpl().now()
    ),
    val newListName: String = "",
    val colors: List<ColorItem> = initialColors(),
    val taskName: TextFieldValue = TextFieldValue()
) {
    val listDisplayable = list.toToDoListState()
    val validListName = newListName.isNotBlank()
    val validTaskName = taskName.text.isNotBlank()

    companion object {
        private fun initialColors(): List<ColorItem> {
            return listOf(
                ColorItem(
                    color = ListRed,
                    applied = false
                ),
                ColorItem(
                    color = ListOrange,
                    applied = false
                ),
                ColorItem(
                    color = ListYellow,
                    applied = false
                ),
                ColorItem(
                    color = ListGreen,
                    applied = false
                ),
                ColorItem(
                    color = ListBlue,
                    applied = true
                ),
                ColorItem(
                    color = ListPurple,
                    applied = false
                ),
                ColorItem(
                    color = ListBrown,
                    applied = false
                )
            )
        }
    }
}

data class ToDoListState(
    val id: String = "",
    val name: String = "",
    val color: ToDoColor = ToDoColor.BLUE,
    val tasks: List<ToDoTaskItem> = listOf()
)

sealed class ToDoTaskItem {
    data class CompleteHeader(val id: String = "CompleteHeader") : ToDoTaskItem()

    data class Complete(
        val toDoTask: ToDoTask
    ) : ToDoTaskItem()

    data class InProgress(
        val toDoTask: ToDoTask
    ) : ToDoTaskItem()
}

data class ColorItem(
    val color: Color,
    val applied: Boolean
)
