package com.example.smart_job_finder_v2.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smart_job_finder_v2.JSFAppState
import com.example.smart_job_finder_v2.Screen
import com.example.smart_job_finder_v2.models.Job
import com.example.smart_job_finder_v2.ui.widgets.BottomBar
import com.example.smart_job_finder_v2.ui.widgets.JobItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    appState: JSFAppState,
    navigate: (String) -> Unit,
    clearAndNavigate: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            /** handle Navigation Click */
            ToolBar(
                onNavigationClick = {
                    /** handle Navigation Click */
                },
                onSettingClick = {
                    viewModel.onSignOut()
                    clearAndNavigate(Screen.SplashScreen.route)
                }
            )
        },
        bottomBar = {
            BottomBar(
                appState
            )
        },
        content = { padding ->
            HomeContent(padding, navigate)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBar(
    onNavigationClick: () -> Unit,
    onSettingClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onSecondary
        ),
        navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Navigation icon"
                )
            }
        },
        title = { Text("Smart Job Finder") },
        actions = {
            IconButton(onClick = onSettingClick) {
                Icon(
                    Icons.Filled.ExitToApp,
                    contentDescription = "Settings"
                )
            }
        })
}

@Composable
fun HomeContent(padding: PaddingValues, navigate: (String) -> Unit) {
    val job = List(10) {
        Job(1, "Software Engineer", "Amazon", "Graz", "Full Time", "Job Description", "Posted Date")
        Job(2, "Software Engineer", "Amazon", "Graz", "Full Time", "Job Description", "Posted Date")
        Job(3, "Software Engineer", "Amazon", "Graz", "Full Time", "Job Description", "Posted Date")
        Job(4, "Software Engineer", "Amazon", "Graz", "Full Time", "Job Description", "Posted Date")
        Job(5, "Software Engineer", "Amazon", "Graz", "Full Time", "Job Description", "Posted Date")
        Job(6, "Software Engineer", "Amazon", "Graz", "Full Time", "Job Description", "Posted Date")
        Job(7, "Software Engineer", "Amazon", "Graz", "Full Time", "Job Description", "Posted Date")
        Job(8, "Software Engineer", "Amazon", "Graz", "Full Time", "Job Description", "Posted Date")
        Job(9, "Software Engineer", "Amazon", "Graz", "Full Time", "Job Description", "Posted Date")
        Job(
            10,
            "Software Engineer",
            "Amazon",
            "Graz",
            "Full Time",
            "Job Description",
            "Posted Date"
        )
    }

    Box(modifier = Modifier.padding(padding)) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(job) { job ->
                JobItem(job, navigate)

            }
        }
    }
}