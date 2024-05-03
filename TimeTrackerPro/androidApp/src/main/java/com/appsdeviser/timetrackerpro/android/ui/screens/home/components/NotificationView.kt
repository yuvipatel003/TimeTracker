package com.appsdeviser.timetrackerpro.android.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LightPrimaryColor
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing
import com.appsdeviser.timetrackerpro.android.ui.core.theme.TextColorBlack
import com.appsdeviser.tracker.presentation.home.components.NotificationState

@Composable
fun NotificationView(
    state: NotificationState,
    modifier: Modifier = Modifier,
    onCloseClick: () -> Unit,
    onNextSwipe: () -> Unit,
    onPreviousSwipe: () -> Unit
) {
    val spacing = LocalSpacing.current
    var swipe by remember { mutableStateOf(-1) }

    if (state.isNotificationOpen) {
        Box(modifier = Modifier
            .padding(spacing.spaceSmall)
            .background(
                color = LightPrimaryColor,
                shape = RoundedCornerShape(spacing.spaceMedium)
            )
            .padding(spacing.spaceSmallMedium)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDrag = { change, dragAmount ->
                        change.consume()
                        val (x, y) = dragAmount
                        when {
                            x > 0 -> { /* right */
                                swipe = 0
                            }

                            x < 0 -> { /* left */
                                swipe = 1
                            }
                        }
                    },
                    onDragEnd = {
                        when (swipe) {
                            0 -> {
                                onPreviousSwipe()
                            }

                            1 -> {
                                onNextSwipe()
                            }

                            else -> {}
                        }
                    })
            }
        ) {
            Icon(
                imageVector = Icons.Default.Cancel,
                contentDescription = null,
                tint = TextColorBlack,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .clickable {
                        onCloseClick()
                    }
            )
            NotificationContent(notificationItem = state.listOfNotification[state.currentPosition])
        }
    }
}