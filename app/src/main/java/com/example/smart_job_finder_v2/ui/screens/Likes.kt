package com.example.smart_job_finder_v2.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.smart_job_finder_v2.ui.widgets.BottomBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LikesScreen(navController: NavController) {
    /** handle Action Click*/
    /** handle Navigation Click */
    Scaffold(
        topBar = {
            ToolBar({ /** handle Navigation Click */ }) {
                /** handle Action Click*/
            }
        },
        bottomBar = {
            BottomBar(
                navController = navController
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