package com.example.smart_job_finder_v2.ui.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.smart_job_finder_v2.models.JobModel
import com.example.smart_job_finder_v2.models.service.AccountService
import com.example.smart_job_finder_v2.ui.SJFViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val accountService: AccountService) :
    SJFViewModel() {

    private val _showBottomSheet = mutableStateOf(false)
    val showBottomSheet: State<Boolean> = _showBottomSheet

    private val _selectedJobModel = mutableStateOf<JobModel?>(null)
    val selectedJobModel: State<JobModel?> = _selectedJobModel
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