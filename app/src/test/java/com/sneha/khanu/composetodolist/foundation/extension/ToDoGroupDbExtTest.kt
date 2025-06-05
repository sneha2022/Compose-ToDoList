package com.sneha.khanu.composetodolist.foundation.extension

import com.wisnu.khanu.composetodolist.DateFactory
import com.wisnu.khanu.composetodolist.foundation.datasource.local.mapper.toGroup
import com.wisnu.khanu.composetodolist.foundation.datasource.local.model.ToDoGroupDb
import com.wisnu.khanu.composetodolist.model.ToDoGroup
import org.junit.Assert
import org.junit.Test

class ToDoGroupDbExtTest {

    @Test
    fun groupDbToGroup() {
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
                ToDoGroupDb(
                    id = "id",
                    name = "name",
                    createdAt = DateFactory.constantDate,
                    updatedAt = DateFactory.constantDate,
                )
            ).toGroup()
        )
    }

}
