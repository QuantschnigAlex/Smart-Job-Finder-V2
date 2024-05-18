package com.example.smart_job_finder_v2.ui.widgets

import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smart_job_finder_v2.ui.screens.home.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobBottomSheet(viewModel: HomeViewModel = hiltViewModel(), scope: CoroutineScope) {
    val showBottomSheet by viewModel.showBottomSheet
    val sheetState = rememberModalBottomSheetState()
    val selectedJob by viewModel.selectedJobModel

    LaunchedEffect(showBottomSheet) {
        if (showBottomSheet) {
            sheetState.show()
        } else {
            sheetState.hide()
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                viewModel.dismissBottomSheet()
            },
            sheetState = sheetState
        ) {
            Text(text = selectedJob!!.title)
            Button(onClick = {
                scope.launch {
                    sheetState.hide()
                }.invokeOnCompletion {
                    viewModel.dismissBottomSheet()
                }
            }) {
                Text("Hide bottom sheet")
            }
        }
    }
}