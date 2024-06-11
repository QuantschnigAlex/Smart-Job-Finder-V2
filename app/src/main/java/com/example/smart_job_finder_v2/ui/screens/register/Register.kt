package com.example.smart_job_finder_v2.ui.screens.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smart_job_finder_v2.R
import com.example.smart_job_finder_v2.ui.widgets.SJFTextField

@Composable
fun RegisterScreen(openAndPopUp: (String, String) -> Unit, viewModel: RegisterViewModel = hiltViewModel()) {

    val email = viewModel.email.collectAsState()
    val password = viewModel.password.collectAsState()
    val confirmPassword = viewModel.confirmPassword.collectAsState()
    val firstName = viewModel.firstName.collectAsState()
    val lastName = viewModel.lastName.collectAsState()
    var passwordVisible by remember { mutableStateOf(false) }
    val errorMessage = viewModel.errorMessage.collectAsState()

    Column(modifier = Modifier
        .padding(32.dp)
        .fillMaxSize()) {
        SJFTextField(value = firstName.value, onValueChange = {viewModel.updateFirstName(it)}, placeholder = { Text(stringResource(R.string.FirstName)) })
        Spacer(modifier =Modifier.height(16.dp))
        SJFTextField(value = lastName.value, onValueChange = {viewModel.updateLastName(it)}, placeholder = { Text(stringResource(R.string.LastName)) })
        Spacer(modifier =Modifier.height(16.dp))
        SJFTextField(value = email.value, onValueChange = {viewModel.updateEmail(it)}, placeholder = { Text(stringResource(R.string.EmailPlaceholder))}, trailingIcon = painterResource(id = R.drawable.email))
        Spacer(modifier =Modifier.height(16.dp))
        OutlinedTextField(
            singleLine = true,
            value = password.value,
            onValueChange = { viewModel.updatePassword(it) },
            placeholder = { Text(stringResource(R.string.PasswordPlaceholder)) },
            keyboardOptions = KeyboardOptions.Default.copy(autoCorrect = false),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible) {
                    painterResource(id = R.drawable.visibility)
                } else {
                    painterResource(id = R.drawable.visibility_off)
                }
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        painter = image,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password"
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier =Modifier.height(16.dp))
        OutlinedTextField(
            singleLine = true,
            value = confirmPassword.value,
            onValueChange = { viewModel.updateConfirmPassword(it) },
            placeholder = { Text(stringResource(R.string.PasswordPlaceholderAgain)) },
            keyboardOptions = KeyboardOptions.Default.copy(autoCorrect = false),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible) {
                    painterResource(id = R.drawable.visibility)
                } else {
                    painterResource(id = R.drawable.visibility_off)
                }
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        painter = image,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password"
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier =Modifier.height(16.dp))
        if (errorMessage.value?.isNotEmpty() == true) {
            Text(
                text = errorMessage.value!!,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                color = Color.Red
            )
        }

        Spacer(modifier =Modifier.height(16.dp))
        ElevatedButton(onClick = {
            viewModel.onSignUpClick(openAndPopUp)

        }, ) {
            Text(text = stringResource(id = R.string.RegisterButton))
        }
    }
}