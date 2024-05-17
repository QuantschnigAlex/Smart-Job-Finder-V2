package com.example.smart_job_finder_v2.ui.screens.splash

import com.example.smart_job_finder_v2.Screen
import com.example.smart_job_finder_v2.models.service.AccountService
import com.example.smart_job_finder_v2.ui.SJFViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val accountService: AccountService
) : SJFViewModel() {

    fun onAppStart(openAndPopUp: (String, String) -> Unit) {
        if (accountService.hasUser()) openAndPopUp(
            Screen.HomeScreen.route,
            Screen.SplashScreen.route
        )
        else openAndPopUp(Screen.SignInScreen.route, Screen.SplashScreen.route)
    }
}