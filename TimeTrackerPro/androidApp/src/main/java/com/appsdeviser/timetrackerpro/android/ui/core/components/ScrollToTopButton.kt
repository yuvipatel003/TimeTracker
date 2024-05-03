package com.appsdeviser.timetrackerpro.android.ui.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing
import kotlinx.coroutines.launch

@Composable
fun ScrollToTopButton(
    state: LazyListState,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    val spacing = LocalSpacing.current

    val showScrollToTopButton by remember {
        derivedStateOf {
            state.firstVisibleItemIndex >= 5
        }
    }

    if (showScrollToTopButton) {
        Box(modifier = modifier
            .clickable {
                scope.launch {
                    state.animateScrollToItem(0)
                }
            }
            .background(MaterialTheme.colorScheme.primary, CircleShape)
            .padding(spacing.spaceSmall)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(spacing.spaceLarge),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}