package com.example.smart_job_finder_v2.ui.widgets

import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.smart_job_finder_v2.R
import com.example.smart_job_finder_v2.SJFAppState
import com.example.smart_job_finder_v2.Screen

@Composable
fun BottomBar(appState: SJFAppState) {
    NavigationBar {
        NavigationBarItem(
            icon = {
                Icon(
                    Icons.Outlined.Home, contentDescription = "Home",
                    modifier = Modifier.size(28.dp)
                )
            },
            label = { Text(stringResource(id = R.string.HomeBottomBar)) },
            selected = appState.navController.currentRoute() == Screen.HomeScreen.route,

            onClick = { appState.navController.navigate(Screen.HomeScreen.route) }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    Icons.Outlined.FavoriteBorder, contentDescription = "Likes",
                    modifier = Modifier.size(28.dp)
                )
            },
            label = { Text(stringResource(id = R.string.LikeBottomBar)) },
            selected = appState.navController.currentRoute() == Screen.LikesScreen.route,
            onClick = { appState.navController.navigate(Screen.LikesScreen.route) }
        )
        NavigationBarItem(selected = appState.navController.currentRoute() == Screen.PostScreen.route,
            onClick = { appState.navController.navigate(Screen.PostScreen.route) },
            icon = {
                Icon(
                    painterResource(id = R.drawable.post),
                    contentDescription = "company",
                    modifier = Modifier.size(28.dp)
                )
            },
            label = { Text(text = stringResource(id = R.string.PostBottomBar)) })

    }
}

@Composable
fun NavController.currentRoute(): String? {
    val currentBackStackEntry by rememberUpdatedState(currentBackStackEntry)
    return currentBackStackEntry?.destination?.route
}