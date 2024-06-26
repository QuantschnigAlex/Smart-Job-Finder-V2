package com.example.smart_job_finder_v2.ui.screens.sign_in

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
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
fun SignInScreen(
    openAndPopUp: (String, String) -> Unit,
    navigate: (String) -> Unit,
    viewModel: SignInViewModel = hiltViewModel(),
) {

    val email = viewModel.email.collectAsState()
    val password = viewModel.password.collectAsState()
    var passwordVisible by remember { mutableStateOf(false) }
    val errorMessage = viewModel.errorMessage.collectAsState()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Text(
            text = stringResource(id = R.string.LogInToFinder),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(top = 42.dp)
        )
        Spacer(modifier = Modifier.height(38.dp))
        Card(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                SJFTextField(
                    singleLine = true,
                    value = email.value,
                    onValueChange = { viewModel.updateEmail(it) },
                    placeholder = { Text(text = stringResource(id = R.string.EmailPlaceholder)) },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email,
                        autoCorrect = false
                    ),
                    isError = errorMessage.value?.isNotEmpty() == true,
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.email),
                            contentDescription = "Email"
                        )
                    }
                )
                Spacer(modifier = Modifier.height(12.dp))
                SJFTextField(
                    singleLine = true,
                    value = password.value,
                    onValueChange = { viewModel.updatePassword(it) },
                    placeholder = { Text(stringResource(R.string.PasswordPlaceholder)) },
                    keyboardOptions = KeyboardOptions.Default.copy(autoCorrect = false),
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    isError = errorMessage.value?.isNotEmpty() == true,
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

                            "The supplied auth credential is incorrect, malformed or has expired." -> stringResource(
                                id = R.string.InvalidEmailOrPassword
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
                    onClick = { viewModel.onSignInClick(openAndPopUp) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.LogInButton),
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    HorizontalDivider(
                        modifier = Modifier.weight(1f),
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = stringResource(id = R.string.Or),
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    HorizontalDivider(
                        modifier = Modifier.weight(1f),
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                TextButton(
                    onClick = { navigate(Screen.RegisterScreen.route) },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = stringResource(id = R.string.RegisterButton))
                }
            }
        }
    }
}