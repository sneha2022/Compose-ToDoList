package com.sneha.khanu.composetodolist.features.todo.scheduled.ui

import com.wisnu.khanu.composetodolist.model.ToDoTask

sealed class ScheduledAction {
    sealed class TaskAction : ScheduledAction() {
        data class Delete(val task: ToDoTask) : TaskAction()
        data class OnToggleStatus(val task: ToDoTask) : TaskAction()
    }
    object ToggleCompleteTaskVisibility : ScheduledAction()
}
