package com.example.smart_job_finder_v2.ui.screens.home

import com.example.smart_job_finder_v2.models.service.AccountService
import com.example.smart_job_finder_v2.ui.SJFViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val accountService: AccountService) :
    SJFViewModel() {

    fun onSignOut() {
        launchCatching {
            accountService.signOut()
        }

    }
}