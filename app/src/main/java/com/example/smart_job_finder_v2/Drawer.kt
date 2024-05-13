package com.example.smart_job_finder_v2

import android.media.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerContent(navController: NavController, drawerState: DrawerState, scope: CoroutineScope) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        //UserProfileHeader()

        DrawerItem(
            "Home",
            Icons.Default.Home,
            Screen.HomeScreen.route,
            navController,
            drawerState,
            scope
        )
        DrawerItem(
            "Likes",
            Icons.Default.Favorite,
            Screen.LikesScreen.route,
            navController,
            drawerState,
            scope
        )
        DrawerItem(
            "Setting",
            Icons.Default.Settings,
            Screen.SettingsScreen.route,
            navController,
            drawerState,
            scope
        )

        Button(onClick = { /*TODO*/ }) {
            Text(text = "Logout")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerItem(
    label: String,
    icon: ImageVector,
    route: String,
    navController: NavController,
    drawerState: DrawerState,
    scope: CoroutineScope
) {
    Row(modifier = Modifier
        .clickable {
            navigateToScreen(navController, drawerState, scope, route)
        }
        .padding(16.dp)) {
        Icon(imageVector = icon, contentDescription = null)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = label)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
fun navigateToScreen(
    navController: NavController,
    drawerState: DrawerState,
    scope: CoroutineScope,
    route: String
) {
    scope.launch {
        drawerState.close()
        navController.navigate(route){
            popUpTo(navController.graph.startDestinationId){
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}


