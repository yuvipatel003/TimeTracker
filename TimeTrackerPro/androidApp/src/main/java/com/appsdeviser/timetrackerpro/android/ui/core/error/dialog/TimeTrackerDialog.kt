package com.appsdeviser.timetrackerpro.android.ui.core.error.dialog

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.appsdeviser.core_common.utils.error.ApiError
import com.appsdeviser.timetrackerpro.android.R
import com.appsdeviser.timetrackerpro.android.ui.core.theme.DialogDimensions
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing
import com.appsdeviser.timetrackerpro.android.ui.core.theme.gradientSurface

@Composable
fun ApiErrorDialog(
    onPositiveAction: (apiError: ApiError?) -> Unit,
    onNegativeAction: () -> Unit,
    apiError: ApiError?
) {
    val spacing = LocalSpacing.current

    val shouldDismiss = remember {
        mutableStateOf(false)
    }
    val currentDialogState = remember {
        mutableStateOf<DialogState?>(null)
    }

    val dialogState: DialogState? = when (apiError) {
        ApiError.Network.SERVICE_UNAVAILABLE -> {
            DialogState(
                title = stringResource(R.string.api_error_service_not_available_title),
                description = stringResource(R.string.api_error_service_not_available_description),
                positiveButtonText = stringResource(R.string.dialog_ok),
                negativeButtonText = null,
                isCancelable = false,
                priority = DialogPriority.HIGH
            )
        }

        ApiError.Network.CLIENT_ERROR,
        ApiError.Network.SERVER_ERROR -> {
            DialogState(
                title = stringResource(R.string.api_error_something_went_wrong_title),
                description = stringResource(R.string.api_error_something_went_wrong_description),
                positiveButtonText = stringResource(R.string.dialog_ok),
                negativeButtonText = null,
                isCancelable = false,
                priority = DialogPriority.HIGH
            )
        }

        ApiError.Network.UNKNOWN_ERROR,
        ApiError.Network.SERIALIZATION_ERROR,
        ApiError.Network.DATA_TRANSFORMATION_ERROR -> {
            DialogState(
                title = stringResource(R.string.api_error_missing_features_title),
                description = stringResource(R.string.api_error_missing_features_description),
                positiveButtonText = stringResource(R.string.dialog_ok),
                negativeButtonText = null,
                isCancelable = false,
                priority = DialogPriority.MEDIUM
            )
        }

        ApiError.NoError.NO_ERROR -> null
        else -> null
    }

    val displayDialogState = when {
        dialogState == null && currentDialogState.value != null -> currentDialogState.value
        currentDialogState.value == null && dialogState != null -> dialogState
        currentDialogState.value == null && dialogState == null -> null
        currentDialogState.value != null && dialogState != null -> {
            if (currentDialogState.value?.priority?.priority!! > dialogState.priority.priority) {
                dialogState
            } else {
                currentDialogState.value
            }
        }

        else -> null
    }

    if (!shouldDismiss.value && displayDialogState != null) {

        currentDialogState.value = displayDialogState

        Dialog(
            onDismissRequest = {
                shouldDismiss.value = true
                currentDialogState.value = null
            },
            properties = DialogProperties(
                dismissOnBackPress = displayDialogState.isCancelable,
                dismissOnClickOutside = displayDialogState.isCancelable
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
                    text = displayDialogState.title,
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

                Text(
                    text = displayDialogState.description,
                    modifier = Modifier
                        .padding(spacing.spaceMedium, spacing.spaceLarge),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.onSurface)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    if (displayDialogState.isCancelable && displayDialogState.negativeButtonText != null) {
                        Text(
                            text = displayDialogState.negativeButtonText,
                            modifier = Modifier
                                .weight(1f)
                                .background(Color.Transparent)
                                .clickable {
                                    shouldDismiss.value = true
                                    onNegativeAction()
                                    currentDialogState.value = null
                                }
                                .padding(spacing.spaceExtraSmall, spacing.spaceSmall),
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onSurface)
                    }


                    Text(
                        text = displayDialogState.positiveButtonText,
                        modifier = Modifier
                            .weight(1f)
                            .background(Color.Transparent)
                            .clickable {
                                shouldDismiss.value = true
                                onPositiveAction(apiError)
                                currentDialogState.value = null
                            }
                            .padding(spacing.spaceExtraSmall, spacing.spaceSmall),
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurface)
                }
            }
        }
    }
}