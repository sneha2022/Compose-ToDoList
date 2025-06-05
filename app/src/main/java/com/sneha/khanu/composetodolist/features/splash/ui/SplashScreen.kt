package com.sneha.khanu.composetodolist.features.splash.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.wisnu.khanu.composetodolist.foundation.viewmodel.HandleEffect
import com.wisnu.khanu.composetodolist.runtime.navigation.AuthFlow
import com.wisnu.khanu.composetodolist.runtime.navigation.HomeFlow
import com.wisnu.khanu.composetodolist.runtime.navigation.MainFlow

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel
) {
    HandleEffect(viewModel) {
        when (it) {
            SplashEffect.NavigateToDashboard -> {
                navController.navigate(HomeFlow.Root.route) {
                    popUpTo(MainFlow.Root.route) {
                        inclusive = true
                    }
                }
            }
            SplashEffect.NavigateToLogin -> {
                navController.navigate(AuthFlow.Root.route) {
                    popUpTo(MainFlow.Root.route) {
                        inclusive = true
                    }
                }
            }
        }
    }
}
