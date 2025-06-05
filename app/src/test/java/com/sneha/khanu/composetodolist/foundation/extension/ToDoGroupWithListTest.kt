package com.sneha.khanu.composetodolist.foundation.extension

import com.wisnu.khanu.composetodolist.DateFactory
import com.wisnu.khanu.composetodolist.foundation.datasource.local.mapper.groupWithListToGroup
import com.wisnu.khanu.composetodolist.foundation.datasource.local.model.ToDoGroupDb
import com.wisnu.khanu.composetodolist.foundation.datasource.local.model.ToDoGroupWithList
import com.wisnu.khanu.composetodolist.model.ToDoGroup
import org.junit.Assert
import org.junit.Test

class ToDoGroupWithListTest {

    @Test
    fun toDoGroupWithListToGroup() {
        Assert.assertEquals(
            listOf(
                ToDoGroup(
                    id = "id",
                    name = "name",
                    lists = listOf(),
                    createdAt = DateFactory.constantDate,
                    updatedAt = DateFactory.constantDate,
                )
            ),
            listOf(
                ToDoGroupWithList(
                    group = ToDoGroupDb(
                        id = "id",
                        name = "name",
                        createdAt = DateFactory.constantDate,
                        updatedAt = DateFactory.constantDate,
                    ),
                    listWithTasks = listOf()
                )
            ).groupWithListToGroup()
        )
    }

}
