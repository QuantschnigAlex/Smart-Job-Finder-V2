package com.example.smart_job_finder_v2.ui.screens.post_job

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smart_job_finder_v2.R
import com.example.smart_job_finder_v2.SJFAppState
import com.example.smart_job_finder_v2.ui.widgets.AnimatedPreloader
import com.example.smart_job_finder_v2.ui.widgets.BottomBar
import com.example.smart_job_finder_v2.ui.widgets.SJFTextField
import com.example.smart_job_finder_v2.ui.widgets.SuccessDialog
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.Locale

@Composable
fun PostJobScreen(appState: SJFAppState, viewModel: PostJobViewModel = hiltViewModel()) {
    val title = viewModel.title.collectAsState()
    val company = viewModel.company.collectAsState()
    val location = viewModel.location.collectAsState()
    val type = viewModel.type.collectAsState()
    val description = viewModel.description.collectAsState()
    val errorMessage = viewModel.errorMessage.collectAsState()
    val postSuccess by viewModel.postSuccess.collectAsState()
    val email = viewModel.email.collectAsState()
    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    val permissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Log.d("PostJobScreen", "Location permission granted")
                fetchLocation(context, fusedLocationClient) { city ->
                    viewModel.updateLocation(city ?: "Location not available")
                }
            } else {
                Log.d("PostJobScreen", "Location permission denied")
            }
        }

    Scaffold(
        bottomBar = { BottomBar(appState = appState) },
        content = { padding ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .fillMaxSize()
                    .verticalScroll(
                        rememberScrollState()
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(padding)
                        .align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally,

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
                        placeholder = { Text(stringResource(id = R.string.EmailPlaceholder)) },
                        value = email.value,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Email,
                            autoCorrect = false
                        ),
                        onValueChange = { viewModel.updateEmail(it) },
                        modifier = Modifier.fillMaxWidth(),
                        isError = errorMessage.value == "Email is empty"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = location.value,
                        onValueChange = { viewModel.updateLocation(it) },
                        modifier = Modifier.fillMaxWidth(),
                        isError = errorMessage.value == "Location is empty",
                        placeholder = { Text(stringResource(id = R.string.Location)) },
                        trailingIcon = {
                            Button(modifier = Modifier.padding(4.dp), onClick = {
                                when {
                                    ContextCompat.checkSelfPermission(
                                        context,
                                        Manifest.permission.ACCESS_FINE_LOCATION
                                    ) == PackageManager.PERMISSION_GRANTED -> {
                                        fetchLocation(context, fusedLocationClient) { city ->
                                            viewModel.updateLocation(
                                                city ?: "Location not available"
                                            )
                                        }
                                    }

                                    else -> {
                                        permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                                    }
                                }
                            }) {
                                Text(text = stringResource(id = R.string.GetLocation))
                            }
                        }
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
                        singleLine = false,
                        maxLines = 8,
                        onValueChange = { viewModel.updateDescription(it) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        isError = errorMessage.value == "Description is empty"
                    )
                    if (errorMessage.value?.isNotEmpty() == true) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = errorMessage.value!!,
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            color = Color.Red
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
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

fun fetchLocation(
    context: Context,
    fusedLocationClient: FusedLocationProviderClient,
    onCityFetched: (String?) -> Unit
) {
    try {
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let {
                val cityName = getCityName(context, it)
                onCityFetched(cityName)
                Log.d("PostJobScreen", "Current city: $cityName")
            } ?: run {
                onCityFetched(null)
                Log.d("PostJobScreen", "Location not available")
            }
        }
    } catch (e: SecurityException) {
        Log.e("PostJobScreen", "Location permission missing", e)
    }
}

fun getCityName(context: Context, location: Location): String? {
    val geocoder = Geocoder(context, Locale.getDefault())
    val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
    return addresses?.get(0)?.locality
}
