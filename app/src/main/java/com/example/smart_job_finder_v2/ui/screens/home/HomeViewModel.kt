package com.example.smart_job_finder_v2.ui.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.smart_job_finder_v2.models.JobModel
import com.example.smart_job_finder_v2.models.UserData
import com.example.smart_job_finder_v2.models.service.AccountService
import com.example.smart_job_finder_v2.models.service.StorageService
import com.example.smart_job_finder_v2.ui.SJFViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val accountService: AccountService,
    private val storageService: StorageService
) :
    SJFViewModel() {

    val jobs = storageService.jobs
    private val _userData = MutableStateFlow<UserData?>(null)
    val userData: StateFlow<UserData?> = _userData

    private val _showBottomSheet = mutableStateOf(false)
    val showBottomSheet: State<Boolean> = _showBottomSheet

    private val _selectedJobModel = mutableStateOf<JobModel?>(null)
    val selectedJobModel: State<JobModel?> = _selectedJobModel

    private val _isLikedJob = mutableStateOf(false)
//    val isLikedJob: State<Boolean> = _isLikedJob

    val likedJobs: MutableStateFlow<List<JobModel>> = MutableStateFlow(emptyList())

    init {
        fetchUserData()
        observeJobs()
    }

    fun isLiked(job: JobModel): Boolean {
        val likedJobID = userData.value?.likedJobID
        return likedJobID?.contains(job.id) ?: false
    }

    fun toggleLike(job: JobModel) {
        viewModelScope.launch {
            try {
                val userId = accountService.currentUserId
                storageService.toggleLike(userId, job.id)
                fetchUserData()
                filterLikedJobs()
            } catch (e: Exception) {
                throw Exception("Error toggling like")
            }
        }
    }

    private fun fetchUserData() {
        viewModelScope.launch {
            try {
                val userId = accountService.currentUserId
                val userData = storageService.getUserData(userId)
                _userData.value = userData
                filterLikedJobs()
            } catch (e: Exception) {

                throw Exception("Error fetching user data")
            }
        }
    }

    private fun filterLikedJobs() {
        viewModelScope.launch {
            val likedJobIds = userData.value?.likedJobID ?: emptyList()
            jobs.collect { allJobs ->
                val filteredJobs = allJobs.filter { likedJobIds.contains(it.id) }
                likedJobs.value = filteredJobs
            }
        }
    }

    private fun observeJobs() {
        viewModelScope.launch {
            jobs.collect {
                filterLikedJobs()
            }
        }
    }

    fun onSignOut() {
        launchCatching {
            accountService.signOut()
        }
    }

    fun showBottomSheet(job: JobModel) {
        _selectedJobModel.value = job
        _showBottomSheet.value = true
    }

    fun dismissBottomSheet() {
        _showBottomSheet.value = !_showBottomSheet.value
    }
}