package com.sneha.khanu.composetodolist.features.todo.grouplist.data

import com.wisnu.khanu.composetodolist.foundation.datasource.local.provider.ToDoListProvider
import com.wisnu.khanu.composetodolist.foundation.wrapper.DateTimeProvider
import com.wisnu.khanu.composetodolist.model.GroupIdWithList
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateGroupListEnvironment @Inject constructor(
    private val toDoListProvider: ToDoListProvider,
    override val dateTimeProvider: DateTimeProvider
) : IUpdateGroupListEnvironment {

    override fun getListWithUnGroupList(groupId: String): Flow<List<GroupIdWithList>> {
        return toDoListProvider.getListWithUnGroupList(groupId)
    }

    override suspend fun updateList(data: List<GroupIdWithList>) {
        val update = data.map { it.copy(list = it.list.copy(updatedAt = dateTimeProvider.now())) }
        toDoListProvider.updateList(update)
    }
}
