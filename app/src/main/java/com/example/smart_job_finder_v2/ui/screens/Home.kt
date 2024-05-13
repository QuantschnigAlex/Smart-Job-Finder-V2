package com.example.smart_job_finder_v2.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.smart_job_finder_v2.DrawerContent
import com.example.smart_job_finder_v2.ToolBar
import com.example.smart_job_finder_v2.models.Job
import com.example.smart_job_finder_v2.ui.widgets.BottomBar
import com.example.smart_job_finder_v2.ui.widgets.JobItemView
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, openDrawer: () -> Unit) {
    Scaffold(
        topBar = {
            ToolBar(onNavigationClick = {
                openDrawer()
            })
        },
        content = { padding ->
            HomeContent(padding, navController)
        },
        bottomBar = {
            BottomBar(navController = navController)
        }
    )
}


@Composable
fun HomeContent(padding: PaddingValues, navController: NavController) {
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
            10, "Software Engineer", "Amazon", "Graz", "Full Time", "Job Description", "Posted Date"
        )
    }

    Box(modifier = Modifier.padding(padding)) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(job) { job ->
                JobItemView(job, navController = navController)

            }
        }
    }
}