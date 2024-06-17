package com.example.smart_job_finder_v2.ui.screens.drawer

import com.example.smart_job_finder_v2.models.service.AccountService
import com.example.smart_job_finder_v2.ui.SJFViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppDrawerViewModel @Inject constructor(
    private val accountService: AccountService,
) : SJFViewModel() {
    fun signOut() {
        launchCatching {
            accountService.signOut()
        }
    }
}