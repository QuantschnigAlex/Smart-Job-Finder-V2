package com.example.smart_job_finder_v2.ui.widgets

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.navigation.NavController
import com.example.smart_job_finder_v2.JSFAppState
import com.example.smart_job_finder_v2.Screen

@Composable
fun BottomBar(appState: JSFAppState) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Outlined.Home, contentDescription = "Home") },
            label = { Text("Home", maxLines = 1) },
            selected = appState.navController.currentRoute() == Screen.HomeScreen.route,

            onClick = { appState.navController.navigate(Screen.HomeScreen.route) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Outlined.FavoriteBorder, contentDescription = "Likes") },
            label = { Text("Likes", maxLines = 1) },
            selected = appState.navController.currentRoute() == Screen.LikesScreen.route,
            onClick = { appState.navController.navigate(Screen.LikesScreen.route) }
        )
    }
}

@Composable
fun NavController.currentRoute(): String? {
    val currentBackStackEntry by rememberUpdatedState(currentBackStackEntry)
    return currentBackStackEntry?.destination?.route
}