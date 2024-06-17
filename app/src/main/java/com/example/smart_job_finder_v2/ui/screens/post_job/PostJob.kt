package com.example.smart_job_finder_v2.ui.screens.post_job

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smart_job_finder_v2.R
import com.example.smart_job_finder_v2.SJFAppState
import com.example.smart_job_finder_v2.ui.widgets.AnimatedPreloader
import com.example.smart_job_finder_v2.ui.widgets.BottomBar
import com.example.smart_job_finder_v2.ui.widgets.SJFTextField
import com.example.smart_job_finder_v2.ui.widgets.SuccessDialog

@Composable
fun PostJobScreen(appState: SJFAppState, viewModel: PostJobViewModel = hiltViewModel()) {
    val title = viewModel.title.collectAsState()
    val company = viewModel.company.collectAsState()
    val location = viewModel.location.collectAsState()
    val type = viewModel.type.collectAsState()
    val description = viewModel.description.collectAsState()
    val errorMessage = viewModel.errorMessage.collectAsState()
    val postSuccess by viewModel.postSuccess.collectAsState()

    Scaffold(
        bottomBar = { BottomBar(appState = appState) },
        content = { padding ->
            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(padding)
                        .align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    if (postSuccess) {
                        SuccessDialog {
                            viewModel.postSuccess.value = false
                        }
                    }

                    AnimatedPreloader(modifier = Modifier.size(200.dp), R.raw.form_animation)
                    Spacer(modifier = Modifier.height(18.dp))

                    SJFTextField(
                        placeholder = { Text(stringResource(id = R.string.JobTitle)) },
                        value = title.value,
                        onValueChange = { viewModel.updateTitle(it) },
                        modifier = Modifier.fillMaxWidth(),
                        isError = errorMessage.value == "Title is empty"
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    SJFTextField(
                        placeholder = { Text(stringResource(id = R.string.Company)) },
                        value = company.value,
                        onValueChange = { viewModel.updateCompany(it) },
                        modifier = Modifier.fillMaxWidth(),
                        isError = errorMessage.value == "Company is empty"
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    SJFTextField(
                        placeholder = { Text(stringResource(id = R.string.Location)) },
                        value = location.value,
                        onValueChange = { viewModel.updateLocation(it) },
                        modifier = Modifier.fillMaxWidth(),
                        isError = errorMessage.value == "Location is empty"
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    SJFTextField(
                        placeholder = { Text(stringResource(id = R.string.Type)) },
                        value = type.value,
                        onValueChange = { viewModel.updateType(it) },
                        modifier = Modifier.fillMaxWidth(),
                        isError = errorMessage.value == "Type is empty"
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    SJFTextField(
                        placeholder = { Text(stringResource(id = R.string.Description)) },
                        value = description.value,
                        onValueChange = { viewModel.updateDescription(it) },
                        modifier = Modifier.fillMaxWidth(),
                        isError = errorMessage.value == "Description is empty"
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    if (errorMessage.value?.isNotEmpty() == true) {
                        Text(
                            text = errorMessage.value!!,
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            color = androidx.compose.ui.graphics.Color.Red
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            viewModel.postJob()
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(id = R.string.PostJob),
                            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                        )
                    }
                }
            }
        }
    )
}
