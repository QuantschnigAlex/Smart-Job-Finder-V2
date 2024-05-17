package com.example.smart_job_finder_v2.ui.screens.sign_in

import com.example.smart_job_finder_v2.Screen
import com.example.smart_job_finder_v2.models.service.AccountService
import com.example.smart_job_finder_v2.ui.SJFViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val accountService: AccountService
) : SJFViewModel() {
    val email = MutableStateFlow("")
    val password = MutableStateFlow("")

    fun updateEmail(newEmail: String) {
        email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        password.value = newPassword
    }

    fun onSignInClick(openAndPopUp: (String, String) -> Unit) {
        launchCatching {
            if (password.value.length < 6) {
                throw Exception("Password must contain at least 6 characters")
            }

            if (!isValidEmail(email.value)) {
                throw Exception("Email is not valid")
            }
            accountService.signIn(email.value, password.value)
            openAndPopUp(Screen.HomeScreen.route, Screen.SignInScreen.route)
        }
    }

    fun onSignUpClick(openAndPopUp: (String, String) -> Unit) {
        launchCatching {
            if (password.value.length < 6) {
                throw Exception("Password must contain at least 6 characters")
            }

            if (!isValidEmail(email.value)) {
                throw Exception("Email is not valid")
            }
            accountService.signUp(email.value, password.value)
            openAndPopUp(Screen.HomeScreen.route, Screen.SignInScreen.route)
        }

    }

    private fun isValidEmail(email: String): Boolean {
        val emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$"

        val pattern = Pattern.compile(emailPattern)

        val matcher = pattern.matcher(email)

        return matcher.matches()
    }
}