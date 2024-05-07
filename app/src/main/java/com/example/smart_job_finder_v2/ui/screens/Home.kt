package com.example.smart_job_finder_v2.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.smart_job_finder_v2.models.Job
import com.example.smart_job_finder_v2.ui.widgets.BottomBar
import com.example.smart_job_finder_v2.ui.widgets.JobItemView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            ToolBar({ /** handle Navigation Click */ }) {
                /** handle Action Click*/
            }
        },
        bottomBar = { BottomBar() },
        content = { padding ->
            HomeContent(padding)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ToolBar(
    onNavigationClick: () -> Unit,
    onSettingClick: () -> Unit
) {

    TopAppBar(
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
                    Icons.Filled.Settings,
                    contentDescription = "Settings"
                )
            }
        })
}

@Composable
fun HomeContent(padding: PaddingValues) {
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
                JobItemView(job)
                Divider()
            }
        }
    }
}


