package com.example.smart_job_finder_v2.ui.screens.post_job

import com.example.smart_job_finder_v2.models.JobModel
import com.example.smart_job_finder_v2.models.service.StorageService
import com.example.smart_job_finder_v2.ui.SJFViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class PostJobViewModel @Inject constructor(
    private val storageService: StorageService
) : SJFViewModel() {
    val title = MutableStateFlow("")
    val company = MutableStateFlow("")
    val location = MutableStateFlow("")
    val type = MutableStateFlow("")
    val description = MutableStateFlow("")
    val postSuccess = MutableStateFlow(false)
    val email = MutableStateFlow("")

    fun postJob(
    ) {
        launchCatching {
            if (!validateField(title.value)) {
                throw Exception("Title is empty")
            }
            if (!validateField(company.value)) {
                throw Exception("Company is empty")
            }
            if (!validateField(location.value)) {
                throw Exception("Location is empty")
            }
            if (!validateField(type.value)) {
                throw Exception("Type is empty")
            }
            if (!validateField(description.value)) {
                throw Exception("Description is empty")
            }

            if (!validateField(email.value)) {
                throw Exception("Email is empty")
            }
            
            if (!isValidEmail(email.value)) {
                throw Exception("Email is not valid")
            }

            val job = JobModel(
                title = title.value,
                company = company.value,
                location = location.value,
                type = type.value,
                description = description.value,
                email = email.value
            )
            storageService.createJob(job)
            postSuccess.value = true
            title.value = ""
            company.value = ""
            location.value = ""
            type.value = ""
            description.value = ""
            email.value = ""
        }


    }

    fun updateTitle(newTitle: String) {
        title.value = newTitle
    }

    fun updateCompany(newCompany: String) {
        company.value = newCompany
    }

    fun updateLocation(newLocation: String) {
        location.value = newLocation
    }

    fun updateType(newType: String) {
        type.value = newType
    }

    fun updateDescription(newDescription: String) {
        description.value = newDescription
    }

    private fun validateField(text: String): Boolean {
        return text.isNotEmpty()
    }

    fun updateEmail(newEmail: String) {
        email.value = newEmail
    }

    private fun isValidEmail(email: String): Boolean {
        val emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$"

        val pattern = Pattern.compile(emailPattern)

        val matcher = pattern.matcher(email)

        return matcher.matches()
    }
}