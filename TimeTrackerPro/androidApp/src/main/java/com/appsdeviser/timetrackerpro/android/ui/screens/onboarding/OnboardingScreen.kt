package com.appsdeviser.timetrackerpro.android.ui.screens.onboarding

import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import com.appsdeviser.onboarding.presentation.onboarding.OnboardingEvent
import com.appsdeviser.onboarding.presentation.onboarding.OnboardingState
import com.appsdeviser.timetrackerpro.android.R
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing
import com.appsdeviser.timetrackerpro.android.ui.core.theme.TextColorBlack
import com.appsdeviser.timetrackerpro.android.ui.screens.onboarding.components.ActionButton
import com.appsdeviser.timetrackerpro.android.ui.screens.onboarding.components.OutlinedActionButton

@Composable
fun OnboardingScreen(
    state: OnboardingState,
    onEvent: (OnboardingEvent) -> Unit
) {
    val spacing = LocalSpacing.current
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = state.event) {
        state.event?.let(onEvent)
    }

    Box(
        modifier = Modifier
            .clickable {
                keyboardController?.hide()
            }
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
                text = stringResource(id = R.string.onboarding_welcome),
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
                        text = stringResource(id = R.string.onboarding_enter_username),
                        style = MaterialTheme.typography.titleSmall
                    )
                },
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    color = TextColorBlack
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
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
                        text = stringResource(id = R.string.onboarding_enter_email),
                        style = MaterialTheme.typography.titleSmall
                    )
                },
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    color = TextColorBlack
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
            )
            Spacer(modifier = Modifier.height(spacing.spaceLarge))
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            OutlinedActionButton(
                text = stringResource(id = R.string.skip),
                onClick = { onEvent(OnboardingEvent.OnSkip) },
                modifier = Modifier
                    .weight(1f)
                    .padding(spacing.spaceSmall)
            )
            ActionButton(
                text = stringResource(id = R.string.onboarding_lets_get_started),
                onClick = { onEvent(OnboardingEvent.OnNext) },
                modifier = Modifier
                    .weight(1f)
                    .padding(spacing.spaceSmall)
            )
        }
    }
}