package com.sneha.khanu.composetodolist.model

import java.time.LocalDateTime

data class ToDoTask(
    val id: String = "",
    val name: String = "",
    val status: ToDoStatus = ToDoStatus.IN_PROGRESS,
    val steps: List<ToDoStep> = listOf(),
    val completedAt: LocalDateTime? = null,
    val dueDate: LocalDateTime? = null,
    val isDueDateTimeSet: Boolean = false,
    val repeat: ToDoRepeat = ToDoRepeat.NEVER,
    val note: String = "",
    val noteUpdatedAt: LocalDateTime? = null,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)
