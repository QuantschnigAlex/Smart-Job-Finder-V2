package com.example.smart_job_finder_v2

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smart_job_finder_v2.ui.screens.DetailsContent
import com.example.smart_job_finder_v2.ui.screens.DetailsScreen
import com.example.smart_job_finder_v2.ui.screens.LikesScreen
import com.example.smart_job_finder_v2.ui.screens.home.HomeScreen
import com.example.smart_job_finder_v2.ui.screens.sign_in.SignInScreen
import com.example.smart_job_finder_v2.ui.screens.splash.SplashScreen
import com.example.smart_job_finder_v2.ui.theme.Smart_job_finder_v2Theme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun JSFApp() {
    Smart_job_finder_v2Theme {
        Surface(color = MaterialTheme.colorScheme.background) {
            val appState = rememberAppState()

            Scaffold { padding ->
                NavHost(
                    navController = appState.navController,
                    startDestination = Screen.SplashScreen.route,
                    modifier = Modifier.padding(padding)
                ) {
                    jsfGraph(appState)
                }

            }
        }
    }
}

@Composable
fun rememberAppState(navController: NavHostController = rememberNavController()) =
    remember(navController) {
        JSFAppState(navController)
    }

fun NavGraphBuilder.jsfGraph(appState: JSFAppState) {
    composable(Screen.HomeScreen.route) {
        HomeScreen(
            appState,
            navigate = { route -> appState.navigate(route) },
            clearAndNavigate = { route -> appState.clearAndNavigate(route) })
    }
    composable(Screen.SplashScreen.route) {
        SplashScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }
    composable(Screen.SignInScreen.route) {
        SignInScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }
    composable(Screen.LikesScreen.route) {
        LikesScreen(appState)
    }
    composable(Screen.DetailsScreen.route) {
        DetailsScreen(popUp = { appState.popUp() })
    }
}