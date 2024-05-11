package com.example.smart_job_finder_v2

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smart_job_finder_v2.ui.screens.DetailsScreen
import com.example.smart_job_finder_v2.ui.screens.HomeScreen
import com.example.smart_job_finder_v2.ui.screens.LikesScreen
import com.example.smart_job_finder_v2.ui.screens.SettingsScreen

@Composable
fun Navigation(modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(route = Screen.LikesScreen.route) {
            LikesScreen(navController)
        }
        composable(route = Screen.DetailsScreen.route) {
            DetailsScreen(navController)
        }
        composable(route = Screen.SettingsScreen.route) {
            SettingsScreen(navController)
        }
    }
}