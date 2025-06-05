package com.sneha.khanu.composetodolist.foundation.extension

import com.wisnu.khanu.composetodolist.DateFactory
import com.wisnu.khanu.composetodolist.foundation.datasource.local.mapper.toToDoList
import com.wisnu.khanu.composetodolist.foundation.datasource.local.model.ToDoListDb
import com.wisnu.khanu.composetodolist.model.ToDoColor
import com.wisnu.khanu.composetodolist.model.ToDoList
import org.junit.Assert
import org.junit.Test

class ToDoListDbExtTest {

    @Test
    fun toList() {
        Assert.assertEquals(
            listOf(
                ToDoList(
                    id = "id",
                    name = "name",
                    color = ToDoColor.RED,
                    tasks = listOf(),
                    createdAt = DateFactory.constantDate,
                    updatedAt = DateFactory.constantDate,
                )
            ),
            listOf(
                ToDoListDb(
                    id = "id",
                    name = "name",
                    color = ToDoColor.RED,
                    groupId = "groupId",
                    createdAt = DateFactory.constantDate,
                    updatedAt = DateFactory.constantDate,
                )
            ).toToDoList()
        )
    }

}
