package com.sneha.khanu.composetodolist.features.todo.step.ui

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.input.TextFieldValue
import com.wisnu.khanu.composetodolist.foundation.wrapper.DateTimeProviderImpl
import com.wisnu.khanu.composetodolist.model.ToDoColor
import com.wisnu.khanu.composetodolist.model.ToDoRepeat
import com.wisnu.khanu.composetodolist.model.ToDoTask
import java.time.LocalDate
import java.time.LocalTime

@Immutable
data class StepState(
    val task: ToDoTask = ToDoTask(
        createdAt = DateTimeProviderImpl().now(),
        updatedAt = DateTimeProviderImpl().now()
    ),
    val color: ToDoColor = ToDoColor.BLUE,
    val editTaskName: TextFieldValue = TextFieldValue(),
    val editStepName: TextFieldValue = TextFieldValue(),
    val createStepName: TextFieldValue = TextFieldValue(),
    val repeatItems: List<ToDoRepeatItem> = initialRepeatItem(),
    val editNote: TextFieldValue = TextFieldValue(),
    val dueDateInitial: LocalDate = DateTimeProviderImpl().now().toLocalDate(),
    val showDueDatePicker: Boolean = false,
    val dueTimeInitial: LocalTime = DateTimeProviderImpl().now().toLocalTime(),
    val showDueTimePicker: Boolean = false,
) {
    val validEditTaskName = editTaskName.text.isNotBlank()
    val validEditStepName = editStepName.text.isNotBlank()
    val validCreateStepName = createStepName.text.isNotBlank()

    companion object {
        private fun initialRepeatItem(): List<ToDoRepeatItem> {
            return listOf(
                ToDoRepeatItem(
                    ToDoRepeat.NEVER,
                    false
                ),
                ToDoRepeatItem(
                    ToDoRepeat.DAILY,
                    false
                ),
                ToDoRepeatItem(
                    ToDoRepeat.WEEKDAYS,
                    false
                ),
                ToDoRepeatItem(
                    ToDoRepeat.WEEKLY,
                    false
                ),
                ToDoRepeatItem(
                    ToDoRepeat.MONTHLY,
                    false
                ),
                ToDoRepeatItem(
                    ToDoRepeat.YEARLY,
                    false
                )
            )
        }
    }
}

data class ToDoRepeatItem(
    val repeat: ToDoRepeat,
    val applied: Boolean
)
