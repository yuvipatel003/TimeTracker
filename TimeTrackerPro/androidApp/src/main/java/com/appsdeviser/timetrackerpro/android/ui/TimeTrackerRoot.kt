package com.appsdeviser.timetrackerpro.android.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.appsdeviser.core_common.utils.error.ApiError
import com.appsdeviser.onboarding.presentation.splash.SplashEvent
import com.appsdeviser.timetrackerpro.android.MainActivity
import com.appsdeviser.timetrackerpro.android.ui.core.error.ErrorUI
import com.appsdeviser.timetrackerpro.android.ui.screens.home.HomeScreen
import com.appsdeviser.timetrackerpro.android.ui.screens.home.WhatsNewScreen
import com.appsdeviser.timetrackerpro.android.ui.screens.onboarding.OnboardingScreen
import com.appsdeviser.timetrackerpro.android.ui.screens.splash.AndroidSplashViewModel
import com.appsdeviser.timetrackerpro.android.ui.screens.splash.SplashScreen

@Composable
fun TimeTrackerRoot(
    activity: MainActivity
) {
    val navController = rememberNavController()
    val rootViewModel = TimeTrackerRootViewModel()
    val errorState = rootViewModel.state

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH) {
        composable(
            route = Routes.SPLASH
        ) {
            val viewModel = hiltViewModel<AndroidSplashViewModel>()
            val state by viewModel.state.collectAsState()
            ErrorUI(
                onPositiveAction = { error ->
                    when (error) {
                        is ApiError -> {
                            when (error) {
                                ApiError.Network.SERVICE_UNAVAILABLE -> activity.finishAffinity()
                                else -> viewModel.onEvent(SplashEvent.OnErrorSeen(state.error))
                            }
                        }
                        else -> {
                            viewModel.onEvent(SplashEvent.OnErrorSeen(state.error))
                        }
                    }
                },
                onNegativeAction = {
                    viewModel.onEvent(SplashEvent.OnErrorSeen(state.error))
                },
                error = state.error
            )
            SplashScreen(
                state = state,
                onEvent = { event ->
                    when(event) {
                        SplashEvent.GoToHomePage -> navController.navigate(Routes.HOME)
                        SplashEvent.GoToOnboarding -> navController.navigate(Routes.ONBOARDING)
                        SplashEvent.GoToWhatsNew -> navController.navigate(Routes.WHATS_NEW)
                        else -> viewModel.onEvent(event)
                    }
                }
            )
        }
        ///////////////////////////////////////////////
        composable(
            route = Routes.HOME
        ) {
            HomeScreen()
        }
        ///////////////////////////////////////////////
        composable(
            route = Routes.ONBOARDING
        ) {
            OnboardingScreen()
        }
        ///////////////////////////////////////////////
        composable(
            route = Routes.WHATS_NEW
        ) {
            WhatsNewScreen()
        }
    }
}