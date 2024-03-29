package com.appsdeviser.timetrackerpro.android.ui.screens.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import com.appsdeviser.onboarding.presentation.onboarding.OnboardingEvent
import com.appsdeviser.onboarding.presentation.onboarding.OnboardingState
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing
import com.appsdeviser.timetrackerpro.android.ui.screens.onboarding.components.ActionButton
import com.appsdeviser.timetrackerpro.android.ui.screens.onboarding.components.OutlinedActionButton

@Composable
fun OnboardingScreen(
    state: OnboardingState,
    onEvent: (OnboardingEvent) -> Unit
) {
    val spacing = LocalSpacing.current

    LaunchedEffect(key1 = state.event) {
        state.event?.let(onEvent)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceMedium, spacing.spaceLarge)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    spacing.spaceMedium,
                    spacing.spaceLarge,
                    spacing.spaceMedium,
                    spacing.spaceExtraLarge
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(spacing.spaceLarge))
            TextField(
                value = state.userName,
                onValueChange = {
                    onEvent(OnboardingEvent.OnUserNameUpdate(it))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(spacing.spaceMedium)),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = "Enter username",
                        style = MaterialTheme.typography.titleSmall
                    )
                },
                textStyle = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            TextField(
                value = state.email,
                onValueChange = {
                    onEvent(OnboardingEvent.OnEmailUpdate(it))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(spacing.spaceMedium)),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = "Enter email",
                        style = MaterialTheme.typography.titleSmall
                    )
                },
                textStyle = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(spacing.spaceLarge))
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(spacing.spaceSmall),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            OutlinedActionButton(
                text = "Skip",
                onClick = { onEvent(OnboardingEvent.OnSkip) },
                modifier = Modifier
                    .weight(1f)
                    .padding(spacing.spaceSmall)
            )
            ActionButton(
                text = "Lets Get Started",
                onClick = { onEvent(OnboardingEvent.OnNext) },
                modifier = Modifier
                    .weight(1f)
                    .padding(spacing.spaceSmall)
            )
        }
    }
}