package com.example.smart_job_finder_v2

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.smart_job_finder_v2.ui.screens.likes.LikesScreen
import com.example.smart_job_finder_v2.ui.screens.home.HomeScreen
import com.example.smart_job_finder_v2.ui.screens.post_job.PostJobScreen
import com.example.smart_job_finder_v2.ui.screens.register.RegisterScreen
import com.example.smart_job_finder_v2.ui.screens.sign_in.SignInScreen
import com.example.smart_job_finder_v2.ui.screens.splash.SplashScreen
import com.example.smart_job_finder_v2.ui.theme.Smart_job_finder_v2Theme
import com.example.smart_job_finder_v2.ui.widgets.ToolBar
import com.example.smart_job_finder_v2.ui.screens.drawer.AppDrawer

@Composable
fun JSFApp() {
    Smart_job_finder_v2Theme {
        Surface(color = MaterialTheme.colorScheme.background) {
            val appState = rememberAppState()
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            val navController = appState.navController
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            AppDrawer(drawerState = drawerState, scope = scope, clearAndNavigate = { route ->
                appState.clearAndNavigate(route)
            }) {
                Scaffold(
                    topBar = {
                        if (shouldShowTopBar(currentRoute)) {
                            ToolBar(
                                title = {
                                    when (currentRoute) {
                                        Screen.HomeScreen.route -> Text(text = stringResource(id = R.string.app_name))
                                        Screen.LikesScreen.route -> Text(text = stringResource(id = R.string.LikeBottomBar))
                                        Screen.PostScreen.route -> Text(text = stringResource(id = R.string.PostJob))
                                        else -> Text(text = "")
                                    }
                                },
                                drawerState,
                                scope
                            )
                        }
                    }
                ) { padding ->
                    NavHost(
                        navController = appState.navController,
                        startDestination = Screen.SplashScreen.route,
                        modifier = Modifier.padding(padding)
                    ) {
                        sjfGraph(appState)
                    }
                }
            }
        }
    }
}

fun shouldShowTopBar(route: String?): Boolean {
    return when (route) {
        Screen.SignInScreen.route, Screen.RegisterScreen.route, Screen.SplashScreen.route -> false
        else -> true
    }
}

@Composable
fun rememberAppState(navController: NavHostController = rememberNavController()) =
    remember(navController) {
        SJFAppState(navController)
    }

fun NavGraphBuilder.sjfGraph(appState: SJFAppState) {
    composable(Screen.HomeScreen.route) {
        HomeScreen(
            appState,
        )
    }
    composable(Screen.SplashScreen.route) {
        SplashScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }
    composable(Screen.SignInScreen.route) {
        SignInScreen(
            openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) },
            navigate = { route -> appState.navigate(route) },
        )
    }
    composable(Screen.LikesScreen.route) {
        LikesScreen(appState)
    }
    composable(Screen.PostScreen.route) {
        PostJobScreen(appState)
    }
    composable(Screen.RegisterScreen.route) {
        RegisterScreen(
            openAndPopUp = { route, popUp ->
                appState.navigateAndPopUp(
                    route,
                    popUp
                )
            },
            navigate = { route -> appState.navigate(route) },
        )
    }
}