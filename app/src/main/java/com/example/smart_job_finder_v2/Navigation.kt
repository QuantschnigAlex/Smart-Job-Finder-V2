package com.example.smart_job_finder_v2

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.smart_job_finder_v2.models.Job
import com.example.smart_job_finder_v2.ui.screens.DetailsScreen
import com.example.smart_job_finder_v2.ui.screens.HomeScreen
import com.example.smart_job_finder_v2.ui.screens.LikesScreen
import com.example.smart_job_finder_v2.ui.screens.SettingsScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            DrawerContent(navController, drawerState, scope)
        },
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
    ) {
        NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
            composable(route = Screen.HomeScreen.route) {
                HomeScreen(navController) {
                    scope.launch {
                        drawerState.open()
                    }
                }
            }
            composable(route = Screen.LikesScreen.route) {
                LikesScreen(navController) {
                    scope.launch {
                        drawerState.open()
                    }
                }
            }
            composable(route = Screen.DetailsScreen.route) {
                DetailsScreen(navController)
                }

            composable(route = Screen.SettingsScreen.route) {
                SettingsScreen(navController)
            }
        }
    }
}