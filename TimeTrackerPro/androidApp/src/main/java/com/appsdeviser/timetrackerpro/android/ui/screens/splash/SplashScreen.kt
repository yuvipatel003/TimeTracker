package com.appsdeviser.timetrackerpro.android.ui.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.appsdeviser.onboarding.presentation.splash.SplashEvent
import com.appsdeviser.onboarding.presentation.splash.SplashState
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing
import com.appsdeviser.timetrackerpro.android.ui.core.theme.changingGradientBackground
import com.appsdeviser.timetrackerpro.android.ui.screens.splash.components.AnalogClock
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    state: SplashState,
    onEvent: (SplashEvent) -> Unit
) {

    val spacing = LocalSpacing.current

    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = state.event) {
        state.event?.let(onEvent)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1.5f,
            animationSpec = tween(
                durationMillis = 2000,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(200L)
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .changingGradientBackground()
            .scale(scale.value)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            AnalogClock(
                modifier = Modifier
                    .height(spacing.splashClockSize)
                    .width(spacing.splashClockSize)
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Text(
                text = "It's all about Time",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            AnimatedContent(
                targetState = state.isLoading,
                label = ""
            ) { loading ->
                if (loading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(spacing.spaceLarge),
                        color = MaterialTheme.colorScheme.onPrimary,
                        strokeWidth = spacing.spaceExtraSmall
                    )
                }
            }
        }
    }
}