package com.sneha.khanu.composetodolist.features.host.ui

import com.wisnu.khanu.composetodolist.model.Theme
import javax.annotation.concurrent.Immutable

@Immutable
data class HostState(val theme: Theme = Theme.SYSTEM)
