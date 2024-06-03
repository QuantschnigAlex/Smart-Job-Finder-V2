package com.example.smart_job_finder_v2.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smart_job_finder_v2.R
import com.example.smart_job_finder_v2.Screen
import com.example.smart_job_finder_v2.ui.screens.home.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobBottomSheet(
    viewModel: HomeViewModel = hiltViewModel(),
    scope: CoroutineScope,
    navigate: (String) -> Unit
) {
    val showBottomSheet by viewModel.showBottomSheet
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
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
            sheetState = sheetState,
            dragHandle = {
                Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                    IconButton(
                        onClick = {
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                viewModel.dismissBottomSheet()
                            }
                        },
                        modifier = Modifier
                            .padding(end = 22.dp, top = 22.dp)
                            .background(Color.White, CircleShape)
                    ) {
                        Icon(
                            Icons.Outlined.Close,
                            contentDescription = "close",
                            Modifier.size(34.dp)
                        )
                    }
                }
            },
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(22.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .size(160.dp)
                            .clip(CircleShape)
                            .background(Color.LightGray)
                            .padding(6.dp) // Optional background color
                    ) {
                        Image(
                            contentDescription = "image",
                            painter = painterResource(id = R.drawable.amazon),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
                item {
                    Spacer(modifier = Modifier.padding(8.dp))
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = selectedJob!!.type,
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Icon(
                            painterResource(id = R.drawable.time),
                            contentDescription = "time",
                            modifier = Modifier.size(36.dp)
                        )
                    }
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = selectedJob!!.title,
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                        )

                        Icon(
                            Icons.Outlined.Person,
                            contentDescription = "job",
                            modifier = Modifier.size(36.dp)
                        )
                    }
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = selectedJob!!.company,
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Icon(
                            painterResource(id = R.drawable.company),
                            contentDescription = "company",
                            modifier = Modifier.size(36.dp)
                        )
                    }
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = selectedJob!!.location,
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Icon(
                            Icons.Outlined.LocationOn,
                            contentDescription = "location",
                            modifier = Modifier.size(36.dp)
                        )
                    }
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = selectedJob!!.postedDate.toString(),
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Icon(
                            Icons.Outlined.DateRange,
                            contentDescription = "posted date",
                            modifier = Modifier.size(36.dp)
                        )
                    }
                }
                item {
                    ExpandableSection(title = stringResource(id = R.string.JobDescription)) {
                        Text(
                            text = selectedJob!!.description,
                            style = MaterialTheme.typography.headlineMedium,
                        )
                    }
                }
                item {
                    ElevatedButton(
                        onClick = {
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                viewModel.dismissBottomSheet()
                            }
                            navigate(Screen.ApplyScreen.route)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 18.dp, bottom = 18.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.Apply),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
