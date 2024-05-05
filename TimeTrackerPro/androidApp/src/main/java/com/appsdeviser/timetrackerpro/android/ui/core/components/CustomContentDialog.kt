package com.appsdeviser.timetrackerpro.android.ui.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.appsdeviser.timetrackerpro.android.ui.core.theme.DialogDimensions
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing
import com.appsdeviser.timetrackerpro.android.ui.core.theme.gradientSurface

@Composable
fun CustomContentDialog(
    showNegativeAction : Boolean = true,
    positiveActionTitle: String = "",
    negativeActionTitle: String = "",
    dialogTitle: String = "",
    dialogContent : @Composable () -> Unit,
    onPositiveAction : () -> Unit,
    onNegativeAction : () -> Unit
) {

    val spacing = LocalSpacing.current
    val shouldDismiss = remember {
        mutableStateOf(false)
    }
    if(!shouldDismiss.value) {
        Dialog(
            onDismissRequest = {
                shouldDismiss.value = true
            },
            properties = DialogProperties(
                dismissOnBackPress = showNegativeAction,
                dismissOnClickOutside = showNegativeAction
            )
        ) {
            Column(
                modifier = Modifier
                    .width(DialogDimensions().width)
                    .wrapContentHeight()
                    .shadow(
                        elevation = spacing.spaceMedium,
                        shape = RoundedCornerShape(spacing.spaceSmall)
                    )
                    .clip(RoundedCornerShape(spacing.spaceSmall))
                    .gradientSurface()
                    .padding(spacing.spaceSmall),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = dialogTitle,
                    modifier = Modifier.padding(spacing.spaceSmall),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(
                    modifier = Modifier
                        .height(2.dp)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.onSurface)
                )
                dialogContent()
                Spacer(
                    modifier = Modifier
                        .height(0.5.dp)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.onSurface)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    if (showNegativeAction) {
                        Text(
                            text = negativeActionTitle,
                            modifier = Modifier
                                .weight(1f)
                                .background(Color.Transparent)
                                .clickable {
                                    onNegativeAction()
                                    shouldDismiss.value = true
                                }
                                .padding(
                                    start = spacing.spaceExtraSmall,
                                    end = spacing.spaceExtraSmall,
                                    bottom = spacing.spaceSmall,
                                    top = spacing.spaceSmallMedium
                                ),
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onSurface)

                        Spacer(
                            modifier = Modifier
                                .width(0.2.dp)
                                .height(spacing.titleBarIconSize)
                                .padding(top = spacing.spaceExtraSmall)
                                .background(MaterialTheme.colorScheme.onBackground)
                        )
                    }


                    Text(
                        text = positiveActionTitle,
                        modifier = Modifier
                            .weight(1f)
                            .background(Color.Transparent)
                            .clickable {
                                onPositiveAction()
                                shouldDismiss.value = true
                            }
                            .padding(
                                start = spacing.spaceExtraSmall,
                                end = spacing.spaceExtraSmall,
                                bottom = spacing.spaceSmall,
                                top = spacing.spaceSmallMedium
                            ),
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurface)
                }
            }
        }
    }
}