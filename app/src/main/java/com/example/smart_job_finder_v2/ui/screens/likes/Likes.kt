package com.example.smart_job_finder_v2.ui.screens.likes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smart_job_finder_v2.R
import com.example.smart_job_finder_v2.SJFAppState
import com.example.smart_job_finder_v2.ui.screens.home.HomeViewModel
import com.example.smart_job_finder_v2.ui.screens.home.ToolBar
import com.example.smart_job_finder_v2.ui.widgets.AnimatedPreloader
import com.example.smart_job_finder_v2.ui.widgets.BottomBar
import com.example.smart_job_finder_v2.ui.widgets.JobItem

@Composable
fun LikesScreen(appState: SJFAppState, viewModel: HomeViewModel = hiltViewModel()) {
    /** handle Action Click*/
    /** handle Navigation Click */
    Scaffold(
        topBar = {
            /** handle Action Click*/
            /** handle Navigation Click */
            ToolBar(
                onNavigationClick = { /*TODO*/ },
                title = { Text(text = stringResource(id = R.string.app_name)) }) {
            }
        },
        bottomBar = {
            BottomBar(
                appState
            )
        },
        content = { padding ->
            LikesContent(padding, viewModel)
        }
    )
}

@Composable
fun LikesContent(padding: PaddingValues, viewModel: HomeViewModel) {
    val likedJobs = viewModel.likedJobs.collectAsState()

    Box(modifier = Modifier.padding(padding), contentAlignment = Alignment.Center) {
        if (likedJobs.value.isEmpty()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                AnimatedPreloader(modifier = Modifier.size(200.dp), R.raw.likes_animation)
                Text(
                    text = stringResource(id = R.string.NOLikedJobs),
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.headlineMedium,
                )
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(likedJobs.value) { job ->
                    JobItem(
                        job,
                        isLiked = true,
                        viewModel = viewModel,
                    )
                }
            }
        }
    }

}