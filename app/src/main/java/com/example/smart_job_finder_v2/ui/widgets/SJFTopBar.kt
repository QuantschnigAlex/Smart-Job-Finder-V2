package com.example.smart_job_finder_v2.ui.widgets

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBar(
    title: @Composable () -> Unit = {},
    drawerState: DrawerState,
    scope: CoroutineScope
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White,
            scrolledContainerColor = MaterialTheme.colorScheme.primary,
            navigationIconContentColor = Color.White
        ),
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    drawerState.open()
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Navigation icon"
                )
            }
        },
        title = { title() },
    )
}

