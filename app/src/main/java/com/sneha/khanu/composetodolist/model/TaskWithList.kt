package com.sneha.khanu.composetodolist.model

data class TaskWithList(
    val list: ToDoList,
    val task: ToDoTask
)
