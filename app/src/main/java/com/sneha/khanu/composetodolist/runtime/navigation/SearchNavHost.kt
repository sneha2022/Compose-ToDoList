package com.sneha.khanu.composetodolist.runtime.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.wisnu.khanu.composetodolist.features.todo.search.ui.SearchScreen
import com.wisnu.khanu.composetodolist.features.todo.search.ui.SearchViewModel

fun NavGraphBuilder.SearchNavHost(
    navController: NavHostController,
) {
    navigation(startDestination = SearchFlow.SearchScreen.route, route = SearchFlow.Root.route) {
        composable(
            route = SearchFlow.SearchScreen.route
        ) {
            val viewModel = hiltViewModel<SearchViewModel>()
            SearchScreen(
                viewModel = viewModel,
                onClickBack = { navController.navigateUp() },
                onTaskItemClick = { taskId, listId -> navController.navigate(StepFlow.Root.route(taskId, listId)) }
            )
        }
    }
}
