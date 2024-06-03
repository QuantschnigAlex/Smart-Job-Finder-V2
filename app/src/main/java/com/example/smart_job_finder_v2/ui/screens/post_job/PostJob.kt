package com.example.smart_job_finder_v2.ui.screens.post_job

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.smart_job_finder_v2.SJFAppState
import com.example.smart_job_finder_v2.models.JobModel
import com.example.smart_job_finder_v2.ui.screens.home.ToolBar
import com.example.smart_job_finder_v2.ui.widgets.BottomBar
import com.google.firebase.Timestamp

@Composable
fun PostJobScreen(appState: SJFAppState) {
    var title by remember { mutableStateOf("") }
    var company by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Scaffold(
        bottomBar = { BottomBar(appState = appState) },
        topBar = {
            ToolBar(onNavigationClick = { /*TODO*/ }) {

            }
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Post a Job", style = MaterialTheme.typography.headlineMedium)

                Spacer(modifier = Modifier.height(16.dp))

                BasicTextField(
                    value = title,
                    onValueChange = { title = it },
                    modifier = Modifier.fillMaxWidth(),
                    decorationBox = { innerTextField ->
                        Row(
                            Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                        ) {
                            if (title.isEmpty()) {
                                Text(
                                    "Title",
                                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f)
                                )
                            }
                            innerTextField()
                        }
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                BasicTextField(
                    value = company,
                    onValueChange = { company = it },
                    modifier = Modifier.fillMaxWidth(),
                    decorationBox = { innerTextField ->
                        Row(
                            Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                        ) {
                            if (company.isEmpty()) {
                                Text(
                                    "Company",
                                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f)
                                )
                            }
                            innerTextField()
                        }
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                BasicTextField(
                    value = location,
                    onValueChange = { location = it },
                    modifier = Modifier.fillMaxWidth(),
                    decorationBox = { innerTextField ->
                        Row(
                            Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                        ) {
                            if (location.isEmpty()) {
                                Text(
                                    "Location",
                                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f)
                                )
                            }
                            innerTextField()
                        }
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                BasicTextField(
                    value = type,
                    onValueChange = { type = it },
                    modifier = Modifier.fillMaxWidth(),
                    decorationBox = { innerTextField ->
                        Row(
                            Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                        ) {
                            if (type.isEmpty()) {
                                Text(
                                    "Type",
                                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f)
                                )
                            }
                            innerTextField()
                        }
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                BasicTextField(
                    value = description,
                    onValueChange = { description = it },
                    modifier = Modifier.fillMaxWidth(),
                    decorationBox = { innerTextField ->
                        Row(
                            Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                        ) {
                            if (description.isEmpty()) {
                                Text(
                                    "Description",
                                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f)
                                )
                            }
                            innerTextField()
                        }
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        val job = JobModel(
                            title = title,
                            company = company,
                            location = location,
                            type = type,
                            description = description,
                            postedDate = Timestamp.now()
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Submit")
                }
            }
        }
    )


}
