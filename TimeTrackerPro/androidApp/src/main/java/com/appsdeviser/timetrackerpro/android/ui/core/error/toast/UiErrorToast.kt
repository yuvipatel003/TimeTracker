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
        UiError.Notification.RECORD_ADDED -> stringResource(R.string.records_updated_successfully)
        UiError.Notification.FAILED_TO_LOAD_MORE_DATA -> stringResource(R.string.failed_to_load_more_data)
        UiError.Notification.FAVOURITE_CATEGORY_DELETE_WARNING -> stringResource(R.string.favourite_category_delete_warning)
        UiError.Notification.ADD_CATEGORY_WARNING -> stringResource(R.string.empty_category_home_screen_message)
        else -> null
    }

    message?.let {
        Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        onUiErrorDisplayed()
    }
}