package com.sneha.khanu.composetodolist.features.todo.main.ui

import app.cash.turbine.test
import com.wisnu.khanu.composetodolist.BaseViewModelTest
import com.wisnu.khanu.composetodolist.DateFactory
import com.wisnu.khanu.composetodolist.features.todo.main.data.IToDoMainEnvironment
import com.wisnu.khanu.composetodolist.foundation.wrapper.DateTimeProvider
import com.wisnu.khanu.composetodolist.foundation.wrapper.DateTimeProviderImpl
import com.wisnu.khanu.composetodolist.model.ToDoGroup
import com.wisnu.khanu.composetodolist.model.ToDoList
import com.wisnu.khanu.composetodolist.model.ToDoTaskOverallCount
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
class ToDoMainViewModelTest : BaseViewModelTest() {

    @Test
    fun init() = runTest {
        val todoMainEnvironment = object : IToDoMainEnvironment {
            override val dateTimeProvider: DateTimeProvider = DateTimeProviderImpl()

            override fun getGroup(): Flow<List<ToDoGroup>> {
                return flow {
                    emit(
                        listOf(
                            ToDoGroup(
                                id = "id",
                                name = "name",
                                lists = listOf(),
                                createdAt = DateFactory.constantDate,
                                updatedAt = DateFactory.constantDate,
                            )
                        )
                    )
                }
            }

            override fun getOverallCount(): Flow<ToDoTaskOverallCount> {
                return flow { ToDoTaskOverallCount(0, 0, 0) }
            }

            override suspend fun deleteList(list: ToDoList) {

            }

        }

        val toDoMainViewModel = ToDoMainViewModel(todoMainEnvironment)

        toDoMainViewModel.state.test {
            Assert.assertEquals(
                listOf(
                    ItemMainState.ItemGroup(
                        group = ToDoGroup(
                            id = "id",
                            name = "name",
                            lists = listOf(),
                            createdAt = DateFactory.constantDate,
                            updatedAt = DateFactory.constantDate,
                        )
                    )
                ),
                awaitItem().items
            )

            cancelAndConsumeRemainingEvents()
        }
    }

}
