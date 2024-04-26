package com.appsdeviser.timetrackerpro.android.ui.core.error.toast

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.appsdeviser.core_common.utils.error.UiError
import com.appsdeviser.timetrackerpro.android.R

@Composable
fun UiErrorToast(
    uiError: UiError,
    onUiErrorDisplayed: () -> Unit,
) {
    val context = LocalContext.current

    val message = when (uiError) {
        UiError.Notification.MISSING_REQUIRED_FIELD -> stringResource(R.string.please_provide_all_required_information)
        UiError.Notification.INVALID_INPUT -> stringResource(R.string.invalid_input)
        UiError.Notification.INVALID_EMAIL -> stringResource(R.string.invalid_email)
        UiError.Notification.INVALID_USERNAME -> stringResource(R.string.invalid_username)
        UiError.Notification.UPDATED -> stringResource(R.string.updated)
        else -> null
    }

    message?.let {
        Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        onUiErrorDisplayed()
    }
}