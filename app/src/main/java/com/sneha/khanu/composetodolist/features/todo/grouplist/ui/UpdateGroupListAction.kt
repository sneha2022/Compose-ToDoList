package com.sneha.khanu.composetodolist.features.todo.grouplist.ui

import com.wisnu.khanu.composetodolist.model.GroupIdWithList

sealed class UpdateGroupListAction {

    data class Change(val item: GroupIdWithList) : UpdateGroupListAction()
    object Submit : UpdateGroupListAction()

}
