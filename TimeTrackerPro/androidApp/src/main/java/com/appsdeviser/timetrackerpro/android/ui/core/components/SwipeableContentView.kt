package com.appsdeviser.timetrackerpro.android.ui.core.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import com.appsdeviser.timetrackerpro.android.R
import com.appsdeviser.timetrackerpro.android.ui.core.theme.PrimaryColor
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


enum class HorizontalDragValue { Settled, StartToEnd, EndToStart }

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SwipeableContentView(
    height: Dp,
    onEdit: () -> Unit,
    onRemove: () -> Unit,
    onAddNewRecord: () -> Unit,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    var boxSize by remember { mutableFloatStateOf(0F) }
    val scope = rememberCoroutineScope()
    val anchors = DraggableAnchors {
        HorizontalDragValue.Settled at 0f
        HorizontalDragValue.StartToEnd at boxSize / 5
        HorizontalDragValue.EndToStart at -boxSize / 5
    }
    val state = remember {
        AnchoredDraggableState(
            initialValue = HorizontalDragValue.Settled,
            positionalThreshold = { distance -> distance * 0.2f },
            velocityThreshold = { 0.2f },
            animationSpec = tween(),
        )
    }
    SideEffect { state.updateAnchors(anchors) }

    val iconsBackgroundColor by animateColorAsState(
        when (state.targetValue) {
            HorizontalDragValue.Settled -> Color.Transparent
            HorizontalDragValue.StartToEnd -> PrimaryColor
            HorizontalDragValue.EndToStart -> Color.Red
        }, label = "change color"
    )
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.2f)
                .height(height)
                .background(iconsBackgroundColor)
                .align(Alignment.CenterStart),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = {
                    scope.launch { state.animateTo(HorizontalDragValue.Settled) }
                    onEdit()
                }
            ) {
                Icon(
                    Icons.Default.Edit,
                    contentDescription = stringResource(R.string.edit),
                    tint = Color.White
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth(0.2f)
                .height(height)
                .background(iconsBackgroundColor)
                .align(Alignment.CenterEnd),
            contentAlignment = Alignment.Center
        ) {
            IconButton(onClick = {
                scope.launch { state.animateTo(HorizontalDragValue.Settled) }
                onRemove()
            }) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = stringResource(R.string.delete),
                    tint = Color.White
                )
            }
        }
        Box(modifier = Modifier
            .graphicsLayer { boxSize = size.width }
            .offset {
                IntOffset(
                    x = state
                        .requireOffset()
                        .roundToInt(), y = 0
                )
            }
            .fillMaxWidth()
            .combinedClickable(
                onClick = {
                    scope.launch { state.animateTo(HorizontalDragValue.Settled) }
                },
                onLongClick = {
                    onAddNewRecord()
                }
            )
            .height(height)
            .anchoredDraggable(state, Orientation.Horizontal)
        ) {
            content()
        }
    }
}
