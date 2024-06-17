package com.example.smart_job_finder_v2

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object LikesScreen : Screen("likes_screen")
    object SettingsScreen : Screen("settings_screen")
    object SignInScreen : Screen("signIn_screen")
    object SplashScreen : Screen("splash_screen")
    object PostScreen : Screen("post_screen")
    object RegisterScreen : Screen("register_screen")
}