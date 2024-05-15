package com.appsdeviser.timetrackerpro.android.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.appsdeviser.core_common.utils.error.ApiError
import com.appsdeviser.onboarding.presentation.onboarding.OnboardingEvent
import com.appsdeviser.onboarding.presentation.splash.SplashEvent
import com.appsdeviser.onboarding.presentation.whatsnew.WhatsNewEvent
import com.appsdeviser.settings.presentation.settings.SettingsEvent
import com.appsdeviser.timetrackerpro.android.MainActivity
import com.appsdeviser.timetrackerpro.android.ui.core.error.ErrorUI
import com.appsdeviser.timetrackerpro.android.ui.screens.category.AndroidCategoryViewModel
import com.appsdeviser.timetrackerpro.android.ui.screens.category.CategoryScreen
import com.appsdeviser.timetrackerpro.android.ui.screens.home.AndroidHomeViewModel
import com.appsdeviser.timetrackerpro.android.ui.screens.home.HomeScreen
import com.appsdeviser.timetrackerpro.android.ui.screens.onboarding.AndroidOnboardingViewModel
import com.appsdeviser.timetrackerpro.android.ui.screens.onboarding.OnboardingScreen
import com.appsdeviser.timetrackerpro.android.ui.screens.record.add.AddRecordScreen
import com.appsdeviser.timetrackerpro.android.ui.screens.record.add.AndroidAddRecordViewModel
import com.appsdeviser.timetrackerpro.android.ui.screens.record.view.AndroidViewRecordViewModel
import com.appsdeviser.timetrackerpro.android.ui.screens.record.view.ViewRecordScreen
import com.appsdeviser.timetrackerpro.android.ui.screens.settings.AndroidSettingsViewModel
import com.appsdeviser.timetrackerpro.android.ui.screens.settings.SettingsScreen
import com.appsdeviser.timetrackerpro.android.ui.screens.splash.AndroidSplashViewModel
import com.appsdeviser.timetrackerpro.android.ui.screens.splash.SplashScreen
import com.appsdeviser.timetrackerpro.android.ui.screens.whatsnew.AndroidWhatsNewViewModel
import com.appsdeviser.timetrackerpro.android.ui.screens.whatsnew.WhatsNewScreen
import com.appsdeviser.tracker.presentation.category.CategoryEvent
import com.appsdeviser.tracker.presentation.home.HomeEvent
import com.appsdeviser.tracker.presentation.record.add.AddRecordEvent
import com.appsdeviser.tracker.presentation.record.view.ViewRecordEvent

@Composable
fun TimeTrackerRoot(
    activity: MainActivity
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Splash
    ) {
        /**
         * SplashScreen
         */
        composable<Routes.Splash>
        {
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
                        SplashEvent.GoToHomePage -> navController.navigate(Routes.Home)
                        SplashEvent.GoToOnboarding -> navController.navigate(Routes.Onboarding)
                        SplashEvent.GoToWhatsNew -> navController.navigate(Routes.WhatsNew)
                        else -> viewModel.onEvent(event)
                    }
                }
            )
        }
        /**
         * HomeScreen
         */
        composable<Routes.Home>
        {
            val viewModel = hiltViewModel<AndroidHomeViewModel>()
            val state by viewModel.state.collectAsState()
            ErrorUI(
                onPositiveAction = {},
                onNegativeAction = { viewModel.onEvent(HomeEvent.OnErrorSeen) },
                error = state.error
            )
            HomeScreen(
                state = state,
                onEvent = { event ->
                    when (event) {
                        is HomeEvent.ShowAddNewRecord -> navController.navigate(
                            Routes.AddRecord(
                                recordId = event.recordItem?.id ?: -1L
                            )
                        )
                        HomeEvent.ShowCategory -> navController.navigate(Routes.Category)
                        HomeEvent.ShowRecords -> navController.navigate(Routes.ViewAllRecord)
                        HomeEvent.ShowSetting -> navController.navigate(Routes.Settings)
                        else -> viewModel.onEvent(event)
                    }
                }
            )
        }
        /**
         * Onboarding Screen
         */
        composable<Routes.Onboarding>
        {
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
                            navController.navigate(Routes.WhatsNew)
                        }

                        else -> viewModel.onEvent(it)
                    }
                })
        }
        /**
         * Whats New Screen
         */
        composable<Routes.WhatsNew>
        {
            val viewModel = hiltViewModel<AndroidWhatsNewViewModel>()
            val state by viewModel.state.collectAsState()
            WhatsNewScreen(
                state,
                onEvent = {
                    when (it) {
                        is WhatsNewEvent.OnFinish -> {
                            navController.navigate(Routes.Home)
                        }

                        else -> viewModel.onEvent(it)
                    }
                }
            )
        }
        /**
         * View Category
         */
        composable<Routes.Category>{
            val viewModel = hiltViewModel<AndroidCategoryViewModel>()
            val state by viewModel.state.collectAsState()
            ErrorUI(
                onPositiveAction = {},
                onNegativeAction = { viewModel.onEvent(CategoryEvent.OnErrorSeen) },
                error = state.error
            )
            CategoryScreen(
                state = state,
                onEvent = { event ->
                    when (event) {
                        is CategoryEvent.AddRecordToCategory -> {
                            navController.navigate(
                                Routes.AddRecord(
                                    categoryId = event.categoryItem.id ?: -1L
                                )
                            )
                        }

                        else -> viewModel.onEvent(event)
                    }
                },
                onBackClick = {
                    navController.navigateUp()
                }
            )
        }
        /**
         * Add Record
         */
        composable<Routes.AddRecord> {
            val viewModel = hiltViewModel<AndroidAddRecordViewModel>()
            val state by viewModel.state.collectAsState()
            val routeArguments = it.toRoute<Routes.AddRecord>()
            var isNewRecord: Boolean by remember {
                mutableStateOf(false)
            }

            LaunchedEffect(Unit) {
                isNewRecord = true
                if(routeArguments.recordId != -1L) {
                    viewModel.onEvent(AddRecordEvent.OnSelectRecord(routeArguments.recordId ))
                    isNewRecord = false
                }
                if(routeArguments.categoryId != -1L) {
                    viewModel.onEvent(AddRecordEvent.OnSelectCategory(routeArguments.categoryId))
                }
            }

            ErrorUI(
                onPositiveAction = {},
                onNegativeAction = { viewModel.onEvent(AddRecordEvent.OnErrorSeen) },
                error = state.error
            )
            AddRecordScreen(
                state = state,
                onEvent = { event ->
                    viewModel.onEvent(event)
                },
                onBackClick = {
                    navController.navigateUp()
                },
                isNewRecord = isNewRecord
            )
        }
        /**
         * View All Records
         */
        composable<Routes.ViewAllRecord> {
            val viewModel = hiltViewModel<AndroidViewRecordViewModel>()
            val state by viewModel.state.collectAsState()

            ViewRecordScreen(
                state = state,
                onEvent = { event ->
                    when (event) {
                        is ViewRecordEvent.SelectRecord -> {
                            navController.navigate(
                                Routes.AddRecord(
                                    recordId = event.recordId
                                )
                            )
                        }
                        is ViewRecordEvent.AddRecord -> {
                            navController.navigate(
                                Routes.AddRecord()
                            )
                        }
                        else -> viewModel.onEvent(event)
                    }

                },
                onBackClick = {
                    navController.navigateUp()
                }
            )
        }
        /**
         * Settings
         */
        composable<Routes.Settings> {
            val viewModel = hiltViewModel<AndroidSettingsViewModel>()
            val state by viewModel.state.collectAsState()

            ErrorUI(
                onPositiveAction = {},
                onNegativeAction = { viewModel.onEvent(SettingsEvent.OnErrorSeen) },
                error = state.error
            )

            SettingsScreen(
                state,
                onEvent = {
                    viewModel.onEvent(it)
                },
                onBackClick = {
                    navController.navigateUp()
                }
            )
        }
    }
}