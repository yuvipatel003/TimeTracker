package com.appsdeviser.timetrackerpro.android.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.appsdeviser.core_common.utils.error.ApiError
import com.appsdeviser.onboarding.presentation.onboarding.OnboardingEvent
import com.appsdeviser.onboarding.presentation.splash.SplashEvent
import com.appsdeviser.onboarding.presentation.whatsnew.WhatsNewEvent
import com.appsdeviser.timetrackerpro.android.MainActivity
import com.appsdeviser.timetrackerpro.android.ui.core.error.ErrorUI
import com.appsdeviser.timetrackerpro.android.ui.screens.category.add.AddCategoryScreen
import com.appsdeviser.timetrackerpro.android.ui.screens.category.view.ViewAllCategoryScreen
import com.appsdeviser.timetrackerpro.android.ui.screens.home.AndroidHomeViewModel
import com.appsdeviser.timetrackerpro.android.ui.screens.home.HomeScreen
import com.appsdeviser.timetrackerpro.android.ui.screens.onboarding.AndroidOnboardingViewModel
import com.appsdeviser.timetrackerpro.android.ui.screens.onboarding.OnboardingScreen
import com.appsdeviser.timetrackerpro.android.ui.screens.record.add.AddRecordScreen
import com.appsdeviser.timetrackerpro.android.ui.screens.record.view.ViewAllRecordScreen
import com.appsdeviser.timetrackerpro.android.ui.screens.settings.SettingsScreen
import com.appsdeviser.timetrackerpro.android.ui.screens.splash.AndroidSplashViewModel
import com.appsdeviser.timetrackerpro.android.ui.screens.splash.SplashScreen
import com.appsdeviser.timetrackerpro.android.ui.screens.whatsnew.AndroidWhatsNewViewModel
import com.appsdeviser.timetrackerpro.android.ui.screens.whatsnew.WhatsNewScreen
import com.appsdeviser.tracker.presentation.home.HomeEvent

@Composable
fun TimeTrackerRoot(
    activity: MainActivity
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        /**
         * SplashScreen
         */
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
                    when (event) {
                        SplashEvent.GoToHomePage -> navController.navigate(Routes.HOME)
                        SplashEvent.GoToOnboarding -> navController.navigate(Routes.ONBOARDING)
                        SplashEvent.GoToWhatsNew -> navController.navigate(Routes.WHATS_NEW)
                        else -> viewModel.onEvent(event)
                    }
                }
            )
        }
        /**
         * HomeScreen
         */
        composable(
            route = Routes.HOME
        ) {
            val viewModel = hiltViewModel<AndroidHomeViewModel>()
            val state by viewModel.state.collectAsState()
            HomeScreen(
                state = state,
                onEvent = { event ->
                    when (event) {
                        HomeEvent.OnErrorSeen -> TODO()
                        HomeEvent.ShowAddNewCategory -> navController.navigate(Routes.ADD_CATEGORY)
                        HomeEvent.ShowAddNewRecord -> navController.navigate(Routes.ADD_RECORD)
                        HomeEvent.ShowCategoryList -> navController.navigate(Routes.VIEW_ALL_CATEGORY)
                        HomeEvent.ShowRecords -> navController.navigate(Routes.VIEW_ALL_RECORD)
                        HomeEvent.ShowSetting -> navController.navigate(Routes.SETTINGS)
                        HomeEvent.StartOrStopRecord -> TODO()
                        else -> viewModel.onEvent(event)
                    }
                }
            )
        }
        /**
         * Onboarding Screen
         */
        composable(
            route = Routes.ONBOARDING
        ) {
            val viewModel = hiltViewModel<AndroidOnboardingViewModel>()
            val state by viewModel.state.collectAsState()
            ErrorUI(
                onPositiveAction = {},
                onNegativeAction = { viewModel.onEvent(OnboardingEvent.OnErrorSeen) },
                error = state.error
            )
            OnboardingScreen(
                state,
                onEvent = {
                    when (it) {
                        OnboardingEvent.OnFinish -> {
                            navController.navigate(Routes.WHATS_NEW)
                        }

                        else -> viewModel.onEvent(it)
                    }
                })
        }
        /**
         * Whats New Screen
         */
        composable(
            route = Routes.WHATS_NEW
        ) {
            val viewModel = hiltViewModel<AndroidWhatsNewViewModel>()
            val state by viewModel.state.collectAsState()
            WhatsNewScreen(
                state,
                onEvent = {
                    when (it) {
                        is WhatsNewEvent.OnFinish -> {
                            navController.navigate(Routes.HOME)
                        }

                        else -> viewModel.onEvent(it)
                    }
                }
            )
        }
        /**
         * Add Category
         */
        composable(
            route = Routes.ADD_CATEGORY
        ) {
            AddCategoryScreen(
                onBackClick = {
                    navController.navigateUp()
                }
            )
        }
        /**
         * View All Category
         */
        composable(
            route = Routes.VIEW_ALL_CATEGORY
        ) {
            ViewAllCategoryScreen(
                onBackClick = {
                    navController.navigateUp()
                }
            )
        }
        /**
         * Add Record
         */
        composable(
            route = Routes.ADD_RECORD
        ) {
            AddRecordScreen(
                onBackClick = {
                    navController.navigateUp()
                }
            )
        }
        /**
         * View All Records
         */
        composable(
            route = Routes.VIEW_ALL_RECORD
        ) {
            ViewAllRecordScreen(
                onBackClick = {
                    navController.navigateUp()
                }
            )
        }
        /**
         * Settings
         */
        composable(
            route = Routes.SETTINGS
        ) {
            SettingsScreen(
                onBackClick = {
                    navController.navigateUp()
                }
            )
        }

    }
}