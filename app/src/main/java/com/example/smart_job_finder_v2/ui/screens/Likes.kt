package com.example.smart_job_finder_v2.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.smart_job_finder_v2.SJFAppState
import com.example.smart_job_finder_v2.ui.screens.home.ToolBar
import com.example.smart_job_finder_v2.ui.widgets.BottomBar

@Composable
fun LikesScreen(appState: SJFAppState) {
    /** handle Action Click*/
    /** handle Navigation Click */
    Scaffold(
        topBar = {
            /** handle Action Click*/
            /** handle Navigation Click */
            ToolBar({ /** handle Navigation Click */ }) {
                /** handle Action Click*/
            }
        },
        bottomBar = {
            BottomBar(
                appState
            )
        },
        content = { padding ->
            LikesContent(padding)
        }
    )
}

@Composable
fun LikesContent(padding: PaddingValues) {
    Box(modifier = Modifier.padding(padding)) {
        Text(text = "Helloooooo Likes :D")
    }

}