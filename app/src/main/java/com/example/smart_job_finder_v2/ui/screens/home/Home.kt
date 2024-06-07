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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smart_job_finder_v2.SJFAppState
import com.example.smart_job_finder_v2.Screen
import com.example.smart_job_finder_v2.R
import com.example.smart_job_finder_v2.ui.widgets.BottomBar
import com.example.smart_job_finder_v2.ui.widgets.JobBottomSheet
import com.example.smart_job_finder_v2.ui.widgets.JobItem

@Composable
fun HomeScreen(
    appState: SJFAppState,
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
                },
                title = { Text(text = stringResource(id = R.string.app_name)) }
            )
        },
        bottomBar = {
            BottomBar(
                appState
            )
        },
        content = { padding ->
            HomeContent(padding, viewModel)
            JobBottomSheet(viewModel = viewModel, scope = scope, navigate)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBar(
    title: @Composable () -> Unit = {},
    onNavigationClick: () -> Unit,
    onSettingClick: () -> Unit,
) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Navigation icon"
                )
            }
        },
        title = { title() },
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
    viewModel: HomeViewModel
) {
    val jobs by viewModel.jobs.collectAsState(emptyList())
    val userData by viewModel.userData.collectAsState()

    Box(modifier = Modifier.padding(padding)) {

        if (userData == null) {
            CircularProgressIndicator()
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(jobs) { job ->
                    userData?.let {
                        println("USER DATA: ${it.likedJobID}")
                        JobItem(
                            job,
                            viewModel = viewModel,
                            isLiked = it.likedJobID.contains(job.id)
                        )

                    }
                }
            }
        }
    }
}