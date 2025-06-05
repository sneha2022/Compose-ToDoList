package com.sneha.khanu.composetodolist.foundation.extension

import com.wisnu.khanu.composetodolist.DateFactory
import com.wisnu.khanu.composetodolist.foundation.datasource.local.mapper.taskWithStepsToTask
import com.wisnu.khanu.composetodolist.foundation.datasource.local.model.ToDoTaskDb
import com.wisnu.khanu.composetodolist.foundation.datasource.local.model.ToDoTaskWithSteps
import com.wisnu.khanu.composetodolist.model.ToDoRepeat
import com.wisnu.khanu.composetodolist.model.ToDoStatus
import com.wisnu.khanu.composetodolist.model.ToDoTask
import org.junit.Assert
import org.junit.Test

class ToDoTaskWithStepsExtTest {

    @Test
    fun toTask() {
        Assert.assertEquals(
            listOf(
                ToDoTask(
                    id = "id",
                    name = "name",
                    status = ToDoStatus.IN_PROGRESS,
                    dueDate = null,
                    repeat = ToDoRepeat.NEVER,
                    steps = listOf(),
                    createdAt = DateFactory.constantDate,
                    updatedAt = DateFactory.constantDate,
                )
            ),
            listOf(
                ToDoTaskWithSteps(
                    task = ToDoTaskDb(
                        id = "id",
                        name = "name",
                        status = ToDoStatus.IN_PROGRESS,
                        dueDate = null,
                        repeat = ToDoRepeat.NEVER,
                        listId = "listId",
                        createdAt = DateFactory.constantDate,
                        updatedAt = DateFactory.constantDate,
                    ),
                    steps = listOf()
                )
            ).taskWithStepsToTask()
        )
    }
}
