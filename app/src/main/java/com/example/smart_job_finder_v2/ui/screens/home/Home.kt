package com.example.smart_job_finder_v2.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smart_job_finder_v2.JSFAppState
import com.example.smart_job_finder_v2.Screen
import com.example.smart_job_finder_v2.models.JobModel
import com.example.smart_job_finder_v2.ui.widgets.BottomBar
import com.example.smart_job_finder_v2.ui.widgets.JobBottomSheet
import com.example.smart_job_finder_v2.ui.widgets.JobItem

@Composable
fun HomeScreen(
    appState: JSFAppState,
    navigate: (String) -> Unit,
    clearAndNavigate: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
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
            HomeContent(padding, navigate, viewModel)
            JobBottomSheet(viewModel = viewModel, scope = scope, navigate)
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
                    Icons.AutoMirrored.Filled.ExitToApp,
                    contentDescription = "Settings"
                )
            }
        })
}

@Composable
fun HomeContent(
    padding: PaddingValues,
    navigate: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val jobModels = listOf(
        JobModel(1, " Engineer", "Amazon", "Graz", "Full Time", "Job Description", "Posted Date"),
        JobModel(2, "Engineer", "Amazon", "Graz", "Full Time", "Job Description", "Posted Date"),
        JobModel(
            3,
            "Software Engineer",
            "Amazon",
            "Graz",
            "Full Time",
            "Job Description",
            "Posted Date"
        ),
        JobModel(4, " Engineer", "Amazon", "Graz", "Full Time", "Job Description", "Posted Date"),
        JobModel(5, " Engineer", "Amazon", "Graz", "Full Time", "Job Description", "Posted Date"),
        JobModel(6, "Software ", "Amazon", "Graz", "Full Time", "Job Description", "Posted Date"),
        JobModel(7, "Software", "Amazon", "Graz", "Full Time", "Job Description", "Posted Date"),
        JobModel(8, "Engineer", "Amazon", "Graz", "Full Time", "Job Description", "Posted Date"),
        JobModel(9, "Software", "Amazon", "Graz", "Full Time", "Job Description", "Posted Date"),
        JobModel(
            10,
            " Engineer",
            "Amazon",
            "Graz",
            "Full Time",
            "Job Description,Description,DescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionvDescriptionDescriptionvDescriptionDescriptionvDescriptionvDescriptionDescriptionDescriptionvDescriptionvvDescriptionvDescriptionDescriptionvDescriptionvDescriptionDescriptionvvvDescriptionDescriptionvDescriptionvDescriptionvvvvvv",
            "Posted Date"
        )
    )


    Box(modifier = Modifier.padding(padding)) {


        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(jobModels) { job ->
                JobItem(job, viewModel = viewModel)

            }
        }
    }
}