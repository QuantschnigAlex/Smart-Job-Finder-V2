package com.example.smart_job_finder_v2.ui.screens.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smart_job_finder_v2.R
import com.example.smart_job_finder_v2.Screen
import com.example.smart_job_finder_v2.ui.widgets.SJFTextField

@Composable
fun RegisterScreen(
    openAndPopUp: (String, String) -> Unit,
    navigate: (String) -> Unit,
    viewModel: RegisterViewModel = hiltViewModel()
) {

    val email = viewModel.email.collectAsState()
    val password = viewModel.password.collectAsState()
    val confirmPassword = viewModel.confirmPassword.collectAsState()
    val firstName = viewModel.firstName.collectAsState()
    val lastName = viewModel.lastName.collectAsState()
    var passwordVisible by remember { mutableStateOf(false) }
    val errorMessage = viewModel.errorMessage.collectAsState()


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.RegisterToFinder),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(top = 42.dp)
        )
        Spacer(modifier = Modifier.height(38.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                SJFTextField(
                    value = firstName.value,
                    onValueChange = { viewModel.updateFirstName(it) },
                    placeholder = { Text(stringResource(R.string.FirstName)) },
                    isError = errorMessage.value == "First name is empty",
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                SJFTextField(
                    value = lastName.value,
                    onValueChange = { viewModel.updateLastName(it) },
                    isError = errorMessage.value == "Last name is empty",
                    placeholder = { Text(stringResource(R.string.LastName)) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                SJFTextField(
                    value = email.value,
                    onValueChange = { viewModel.updateEmail(it) },
                    isError = errorMessage.value == "Email is not valid" || errorMessage.value == "The email address is already in use by another account.",
                    placeholder = { Text(stringResource(R.string.EmailPlaceholder)) },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.email),
                            contentDescription = "email"
                        )
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email,
                        autoCorrect = false
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                SJFTextField(
                    singleLine = true,
                    value = password.value,
                    isError = errorMessage.value == "Password must contain at least 6 characters" || errorMessage.value == "Passwords do not match",
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
                Spacer(modifier = Modifier.height(16.dp))
                SJFTextField(
                    singleLine = true,
                    value = confirmPassword.value,
                    onValueChange = { viewModel.updateConfirmPassword(it) },
                    isError = errorMessage.value == "Passwords do not match",
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
                Spacer(modifier = Modifier.height(16.dp))
                if (errorMessage.value?.isNotEmpty() == true) {
                    Text(
                        text = when (errorMessage.value) {
                            "Password must contain at least 6 characters" -> stringResource(id = R.string.PasswordNotValid)
                            "Email is not valid" -> stringResource(id = R.string.EmailNotValid)
                            "Passwords do not match" -> stringResource(id = R.string.PasswordsNotMatch)
                            "First name is empty" -> stringResource(id = R.string.FirstNameEmpty)
                            "Last name is empty" -> stringResource(id = R.string.LastNameEmpty)
                            "The email address is already in use by another account." -> stringResource(
                                id = R.string.EmailAlreadyInUse
                            )

                            else -> errorMessage.value!!
                        },
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        textAlign = TextAlign.Center,
                        color = Color.Red
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { viewModel.onSignUpClick(openAndPopUp) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(id = R.string.RegisterButton))
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = stringResource(id = R.string.AlreadyHaveAnAccount),
                    )
                    TextButton(onClick = { navigate(Screen.SignInScreen.route) }) {
                        Text(
                            text = stringResource(id = R.string.LogInButton),
                        )
                    }
                }
            }
        }
    }
}