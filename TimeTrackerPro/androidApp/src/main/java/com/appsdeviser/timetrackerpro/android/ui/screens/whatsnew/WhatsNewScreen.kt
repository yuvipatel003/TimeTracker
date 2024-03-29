package com.appsdeviser.timetrackerpro.android.ui.screens.whatsnew

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.filled.Brightness1
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.appsdeviser.onboarding.presentation.whatsnew.WhatsNewEvent
import com.appsdeviser.onboarding.presentation.whatsnew.WhatsNewItem
import com.appsdeviser.onboarding.presentation.whatsnew.WhatsNewState
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing
import com.appsdeviser.timetrackerpro.android.ui.screens.whatsnew.components.WhatsNewContent

@Composable
fun WhatsNewScreen(
    state: WhatsNewState,
    onEvent: (WhatsNewEvent) -> Unit
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
        val selectedItem: WhatsNewItem? = if (state.list.isNotEmpty()) {
            state.list[state.currentSelection]
        } else null

        selectedItem?.let {
            WhatsNewContent(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.CenterStart),
                imageUrl = selectedItem.image,
                title = selectedItem.title,
                description = selectedItem.description
            )
        }
        LazyRow(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            items(state.list.size) { item ->
                val size = if (item == state.currentSelection) {
                    spacing.spaceMedium
                } else {
                    spacing.spaceSmall
                }
                Icon(
                    imageVector = Icons.Default.Brightness1,
                    contentDescription = null,
                    modifier = Modifier
                        .size(size)
                        .padding(1.dp)
                )
            }
        }

        Button(
            onClick = {
                onEvent(WhatsNewEvent.OnNext)
            },
            modifier = Modifier
                .align(Alignment.BottomEnd),
            shape = CircleShape,
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.ArrowForward, contentDescription = null,
                modifier = Modifier.padding(0.dp, spacing.spaceMedium)
            )
        }

        if (state.currentSelection != 0) {
            Button(
                onClick = {
                    onEvent(WhatsNewEvent.OnPrevious)
                },
                modifier = Modifier
                    .align(Alignment.BottomStart),
                shape = CircleShape,
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = null,
                    modifier = Modifier.padding(0.dp, spacing.spaceMedium)
                )
            }
        }

        Text(
            text = "Skip",
            modifier = Modifier
                .clickable {
                    onEvent(WhatsNewEvent.OnFinish)
                }
                .align(Alignment.TopEnd),
            style = MaterialTheme.typography.headlineSmall
        )

    }
}