package com.sneha.khanu.composetodolist.runtime

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Surface
import androidx.core.view.WindowCompat
import com.wisnu.khanu.composetodolist.R
import com.wisnu.khanu.composetodolist.features.host.ui.Host
import com.wisnu.khanu.composetodolist.foundation.window.WindowState
import com.wisnu.khanu.composetodolist.foundation.window.rememberWindowState
import com.wisnu.khanu.composetodolist.runtime.navigation.MainNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var windowState: WindowState

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_ComposeToDoList_Light)
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            windowState = rememberWindowState()

            Host {
                Surface {
                    MainNavHost(windowState)
                }
            }
        }
    }
}

