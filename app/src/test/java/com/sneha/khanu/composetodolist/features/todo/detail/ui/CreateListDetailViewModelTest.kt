package com.sneha.khanu.composetodolist.features.todo.detail.ui

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.wisnu.khanu.composetodolist.BaseViewModelTest
import com.wisnu.khanu.composetodolist.DateFactory
import com.wisnu.khanu.composetodolist.features.todo.detail.data.IListDetailEnvironment
import com.wisnu.khanu.composetodolist.foundation.theme.ListBlue
import com.wisnu.khanu.composetodolist.foundation.theme.ListOrange
import com.wisnu.khanu.composetodolist.foundation.theme.ListRed
import com.wisnu.khanu.composetodolist.foundation.wrapper.DateTimeProvider
import com.wisnu.khanu.composetodolist.foundation.wrapper.DateTimeProviderImpl
import com.wisnu.khanu.composetodolist.foundation.wrapper.IdProvider
import com.wisnu.khanu.composetodolist.foundation.wrapper.IdProviderImpl
import com.wisnu.khanu.composetodolist.model.ToDoColor
import com.wisnu.khanu.composetodolist.model.ToDoList
import com.wisnu.khanu.composetodolist.model.ToDoTask
import com.wisnu.khanu.composetodolist.runtime.navigation.ARG_LIST_ID
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
class CreateListDetailViewModelTest : BaseViewModelTest() {

    @Test
    fun init() = runTest {
        val environment = emptyEnvironment()
        val savedStateHandle = SavedStateHandle()

        val viewModel = ListDetailViewModel(
            savedStateHandle,
            environment
        )

        viewModel.effect.test {
            Assert.assertEquals(ListDetailEffect.ShowCreateListInput, awaitItem())

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun applyColor() = runTest {
        val environment = emptyEnvironment()
        val savedStateHandle = SavedStateHandle()

        val viewModel = ListDetailViewModel(
            savedStateHandle,
            environment
        )

        viewModel.state.test {
            viewModel.dispatch(ListDetailAction.ListAction.ApplyColor(ColorItem(ListBlue, false)))

            Assert.assertEquals(ListBlue, awaitItem().colors.find { it.applied }?.color)

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun createList() = runTest {
        val environment = buildEnvironment(
            ToDoList(
                id = "id",
                name = "name",
                color = ToDoColor.ORANGE,
                tasks = listOf(),
                createdAt = DateFactory.constantDate,
                updatedAt = DateFactory.constantDate,
            )
        )
        val savedStateHandle = SavedStateHandle()

        val viewModel = ListDetailViewModel(
            savedStateHandle,
            environment
        )

        viewModel.dispatch(ListDetailAction.ListAction.Create)

        viewModel.effect.test {
            Assert.assertEquals(ListDetailEffect.Relaunch("id"), awaitItem())

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun changeListName() = runTest {
        val environment = emptyEnvironment()
        val savedStateHandle = SavedStateHandle()

        val viewModel = ListDetailViewModel(
            savedStateHandle,
            environment
        )

        viewModel.dispatch(ListDetailAction.ListAction.ChangeName("new name"))

        viewModel.state.test {
            Assert.assertEquals(
                "new name",
                awaitItem().newListName
            )

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun updateList() = runTest {
        val environment = emptyEnvironment()
        val savedStateHandle = SavedStateHandle()

        val viewModel = ListDetailViewModel(
            savedStateHandle,
            environment
        )

        viewModel.dispatch(ListDetailAction.ListAction.Create)
        viewModel.dispatch(ListDetailAction.ListAction.ApplyColor(ColorItem(ListRed, false)))

        viewModel.state.test {
            viewModel.dispatch(ListDetailAction.ListAction.ChangeName("new name"))
            viewModel.dispatch(ListDetailAction.ListAction.Update)

            Assert.assertEquals(
                ListRed,
                awaitItem().colors.find { it.applied }?.color
            )
            Assert.assertEquals(
                "new name",
                awaitItem().newListName
            )

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun cancelUpdateList() = runTest {
        val environment = buildEnvironment(
            returnedList = ToDoList(
                id = "id",
                name = "name",
                color = ToDoColor.ORANGE,
                tasks = listOf(),
                createdAt = DateFactory.constantDate,
                updatedAt = DateFactory.constantDate,
            )
        )
        val savedStateHandle = SavedStateHandle().apply {
            set(ARG_LIST_ID, "id")
        }

        val viewModel = ListDetailViewModel(
            savedStateHandle,
            environment
        )

        viewModel.state.test {
            viewModel.dispatch(ListDetailAction.ListAction.ApplyColor(ColorItem(ListRed, false)))
            viewModel.dispatch(ListDetailAction.ListAction.ChangeName("new name"))
            viewModel.dispatch(ListDetailAction.ListAction.CancelUpdate)

            val expectedState = awaitItem()
            Assert.assertEquals(
                ListOrange,
                expectedState.colors.find { it.applied }?.color
            )
            Assert.assertEquals(
                "name",
                expectedState.newListName
            )

            cancelAndConsumeRemainingEvents()
        }
    }

    // TODO: TEST
    @Test
    fun clickImeDone() {

    }

    @Test
    fun changeTaskName() {

    }

    @Test
    fun onTaskShow() {

    }

    private fun emptyEnvironment() = object : IListDetailEnvironment {
        override val idProvider: IdProvider = IdProviderImpl()
        override val dateTimeProvider: DateTimeProvider = DateTimeProviderImpl()
        override fun getListWithTasksById(listId: String): Flow<ToDoList> {
            return flow {}
        }

        override suspend fun createList(toDoList: ToDoList): Flow<ToDoList> {
            return flow {}
        }

        override suspend fun updateList(toDoList: ToDoList): Flow<Any> {
            return flow { emit(Any()) }
        }

        override suspend fun createTask(taskName: String, listId: String) {

        }

        override suspend fun toggleTaskStatus(toDoTask: ToDoTask) {

        }

        override suspend fun deleteTask(toDoTask: ToDoTask) {

        }

        override fun trackSaveListButtonClicked() {

        }
    }

    private fun buildEnvironment(
        returnedCreatedList: ToDoList? = null,
        returnedList: ToDoList? = null,
    ) = object : IListDetailEnvironment {
        override val idProvider: IdProvider = IdProviderImpl()
        override val dateTimeProvider: DateTimeProvider = DateTimeProviderImpl()
        override fun getListWithTasksById(listId: String): Flow<ToDoList> {
            return flow {
                if (returnedList != null) emit(returnedList)
            }
        }

        override suspend fun createList(toDoList: ToDoList): Flow<ToDoList> {
            return flow {
                if (returnedCreatedList != null) emit(returnedCreatedList)
            }
        }

        override suspend fun updateList(toDoList: ToDoList): Flow<Any> {
            return flow { emit(Any()) }
        }

        override suspend fun createTask(taskName: String, listId: String) {

        }

        override suspend fun toggleTaskStatus(toDoTask: ToDoTask) {

        }

        override suspend fun deleteTask(toDoTask: ToDoTask) {

        }

        override fun trackSaveListButtonClicked() {

        }
    }

}
