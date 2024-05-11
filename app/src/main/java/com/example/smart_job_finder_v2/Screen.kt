package com.example.smart_job_finder_v2

import com.example.smart_job_finder_v2.models.Job

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object LikesScreen : Screen("likes_screen")
    object DetailsScreen : Screen("details_screen")
    object SettingsScreen : Screen("settings_screen")

}