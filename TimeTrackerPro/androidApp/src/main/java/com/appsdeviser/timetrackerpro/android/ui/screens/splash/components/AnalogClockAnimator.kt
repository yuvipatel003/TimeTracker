package com.appsdeviser.timetrackerpro.android.ui.screens.splash.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp
import java.util.Calendar

@Composable
fun AnalogClock(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onPrimary
) {
    val currentTime by remember { mutableStateOf(Calendar.getInstance()) }

    // Animation for the clock hands
    val rotationAnim by rememberInfiniteTransition(label = "").animateFloat(
        initialValue = currentTime.get(Calendar.SECOND) * 6f, // 6 degrees per second
        targetValue = (currentTime.get(Calendar.SECOND) + 60) * 6f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 60000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Canvas(modifier = Modifier.size(200.dp)) {
            val centerX = size.width / 2
            val centerY = size.height / 2
            val radius = size.width / 2 - 16 // Adjust the radius as needed

            // Draw clock circle
            drawCircle(
                color = color,
                radius = radius,
                style = Stroke(width = 4.dp.toPx())
            )

            // Draw clock numbers
            repeat(12) { hour ->
                val angle = Math.toRadians((hour * 30 - 90).toDouble())
                val x = (centerX + radius * Math.cos(angle)).toFloat()
                val y = (centerY + radius * Math.sin(angle)).toFloat()
                drawCircle(
                    color = color,
                    radius = 4.dp.toPx(),
                    center = Offset(x, y)
                )
            }

            drawCircle(
                color = color,
                radius = 5.dp.toPx(),
                center = Offset(centerX, centerY)
            )

            // Draw clock hands
            val hourAngle =
                (currentTime.get(Calendar.HOUR) * 30f) + (currentTime.get(Calendar.MINUTE) * 0.5f)
            val minuteAngle = currentTime.get(Calendar.MINUTE) * 6f
            val secondAngle = rotationAnim

            rotate(hourAngle) {
                drawLine(
                    color = color,
                    start = Offset(centerX, centerY),
                    end = Offset(centerX, centerY - radius * 0.45f),
                    strokeWidth = 8.dp.toPx()
                )
            }

            rotate(minuteAngle) {
                drawLine(
                    color = color,
                    start = Offset(centerX, centerY),
                    end = Offset(centerX, centerY - radius * 0.7f),
                    strokeWidth = 4.dp.toPx()
                )
            }

            rotate(secondAngle) {
                drawLine(
                    color = color,
                    start = Offset(centerX, centerY),
                    end = Offset(centerX, centerY - radius * 0.85f),
                    strokeWidth = 2.dp.toPx()
                )
            }
        }
    }
}