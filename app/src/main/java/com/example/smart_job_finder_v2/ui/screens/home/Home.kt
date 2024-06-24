package com.example.smart_job_finder_v2.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smart_job_finder_v2.SJFAppState
import com.example.smart_job_finder_v2.ui.widgets.BottomBar
import com.example.smart_job_finder_v2.ui.widgets.JobBottomSheet
import com.example.smart_job_finder_v2.ui.widgets.JobItem

@Composable
fun HomeScreen(
    appState: SJFAppState,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()

    Scaffold(
        bottomBar = {
            BottomBar(
                appState
            )
        },
        content = { padding ->
            HomeContent(padding, viewModel)
            JobBottomSheet(viewModel = viewModel, scope = scope)
        }
    )
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