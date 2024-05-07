package com.appsdeviser.timetrackerpro.android.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
                        HomeEvent.ShowAddNewRecord -> navController.navigate(
                            Routes.ADD_RECORD
                                    + "/${RoutesArguments.DEFAULT_RECORD_ID_VALUE}"
                                    + "/${RoutesArguments.DEFAULT_CATEGORY_ID_VALUE}"
                        )

                        HomeEvent.ShowCategory -> navController.navigate(Routes.CATEGORY)
                        HomeEvent.ShowRecords -> navController.navigate(Routes.VIEW_ALL_RECORD)
                        HomeEvent.ShowSetting -> navController.navigate(Routes.SETTINGS)
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
         * View Category
         */
        composable(
            route = Routes.CATEGORY
        ) {
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
                            navController.navigate(Routes.ADD_RECORD + "/${RoutesArguments.DEFAULT_RECORD_ID_VALUE}" + "/${event.categoryItem.id}")
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
        composable(
            route = Routes.ADD_RECORD + "/{${RoutesArguments.RECORD_ID}}" + "/{${RoutesArguments.CATEGORY_ID}}",
            arguments = listOf(
                navArgument(RoutesArguments.RECORD_ID) {
                    type = NavType.LongType
                },
                navArgument(RoutesArguments.CATEGORY_ID) {
                    type = NavType.LongType
                }
            )
        ) {
            val viewModel = hiltViewModel<AndroidAddRecordViewModel>()
            val state by viewModel.state.collectAsState()
            val selectedRecordId: Long = it.arguments?.getLong(RoutesArguments.RECORD_ID) ?: -1L
            val selectedCategoryId: Long = it.arguments?.getLong(RoutesArguments.CATEGORY_ID) ?: -1L
            var isNewRecord: Boolean by remember {
                mutableStateOf(false)
            }

            LaunchedEffect(Unit) {
                isNewRecord = true
                if (selectedRecordId != -1L) {
                    viewModel.onEvent(AddRecordEvent.OnSelectRecord(selectedRecordId))
                    isNewRecord = false
                }
                // Optionally, load data for selected category
                if (selectedCategoryId != -1L) {
                    viewModel.onEvent(AddRecordEvent.OnSelectCategory(selectedCategoryId))
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
        composable(
            route = Routes.VIEW_ALL_RECORD
        ) {
            val viewModel = hiltViewModel<AndroidViewRecordViewModel>()
            val state by viewModel.state.collectAsState()

            ViewRecordScreen(
                state = state,
                onEvent = { event ->
                    when (event) {
                        is ViewRecordEvent.SelectRecord -> {
                            navController.navigate(Routes.ADD_RECORD + "/${event.recordId}" + "/${RoutesArguments.DEFAULT_CATEGORY_ID_VALUE}")
                        }
                        is ViewRecordEvent.AddRecord -> {
                            navController.navigate(Routes.ADD_RECORD + "/${RoutesArguments.DEFAULT_RECORD_ID_VALUE}" + "/${RoutesArguments.DEFAULT_CATEGORY_ID_VALUE}")
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
        composable(
            route = Routes.SETTINGS
        ) {
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