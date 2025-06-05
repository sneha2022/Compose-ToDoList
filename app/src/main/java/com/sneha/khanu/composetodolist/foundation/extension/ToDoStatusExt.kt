package com.sneha.khanu.composetodolist.foundation.extension

import com.wisnu.khanu.composetodolist.model.ToDoStatus

fun ToDoStatus.toggle(): ToDoStatus {
    return when (this) {
        ToDoStatus.IN_PROGRESS -> ToDoStatus.COMPLETE
        ToDoStatus.COMPLETE -> ToDoStatus.IN_PROGRESS
    }
}
