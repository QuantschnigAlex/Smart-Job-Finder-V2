package com.example.smart_job_finder_v2.ui.screens.register

import com.example.smart_job_finder_v2.Screen
import com.example.smart_job_finder_v2.models.UserData
import com.example.smart_job_finder_v2.models.service.AccountService
import com.example.smart_job_finder_v2.models.service.StorageService
import com.example.smart_job_finder_v2.ui.SJFViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val accountService: AccountService,
    private val storageService: StorageService
) : SJFViewModel() {
    val email = MutableStateFlow("")
    val password = MutableStateFlow("")
    val confirmPassword = MutableStateFlow("")
    val firstName = MutableStateFlow("")
    val lastName = MutableStateFlow("")

    fun updateEmail(newEmail: String) {
        email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        password.value = newPassword
    }

    fun updateFirstName(newFirstName: String) {
        firstName.value = newFirstName
    }

    fun updateLastName(newLastName: String) {
        lastName.value = newLastName
    }

    fun updateConfirmPassword(newConfirmPassword: String) {
        confirmPassword.value = newConfirmPassword
    }

    fun onSignUpClick(openAndPopUp: (String, String) -> Unit) {

        launchCatching {
            if (password.value != confirmPassword.value) {
                throw Exception("Passwords do not match")
            }

            if (password.value.length < 6) {
                throw Exception("Password must contain at least 6 characters")
            }

            if (!isValidEmail(email.value)) {
                throw Exception("Email is not valid")
            }

            if (firstName.value.isEmpty()) {
                throw Exception("First name is empty")
            }

            if (lastName.value.isEmpty()) {
                throw Exception("Last name is empty")
            }
            accountService.signUp(email.value, password.value)

            val user = UserData(
                email = email.value,
                firstName = firstName.value,
                lastName = lastName.value
            )

            storageService.createUserData(user)

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