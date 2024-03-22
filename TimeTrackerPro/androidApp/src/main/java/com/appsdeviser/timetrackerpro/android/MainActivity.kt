package com.appsdeviser.timetrackerpro.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.appsdeviser.core_common.utils.error.ApiError
import com.appsdeviser.onboarding.presentation.splash.SplashEvent
import com.appsdeviser.timetrackerpro.android.ui.Routes
import com.appsdeviser.timetrackerpro.android.ui.TimeTrackerRoot
import com.appsdeviser.timetrackerpro.android.ui.core.theme.TimeTrackerTheme
import com.appsdeviser.timetrackerpro.android.ui.core.error.ErrorUI
import com.appsdeviser.timetrackerpro.android.ui.screens.splash.AndroidSplashViewModel
import com.appsdeviser.timetrackerpro.android.ui.screens.splash.SplashScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimeTrackerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TimeTrackerRoot(this)
                }
            }
        }
    }
}
