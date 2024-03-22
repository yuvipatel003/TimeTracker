package com.appsdeviser.timetrackerpro.android.ui.core.error.toast

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.appsdeviser.core_common.utils.error.UiError
import com.appsdeviser.timetrackerpro.android.R

@Composable
fun UiErrorToast(
    uiError: UiError
) {
    val context = LocalContext.current

    val message = when (uiError) {
        UiError.Notification.MISSING_REQUIRED_FIELD -> context.getString(R.string.please_provide_all_required_information)
        UiError.Notification.INVALID_INPUT -> context.getString(R.string.invalid_input)
        else -> null
    }

    message?.let {
        Toast.makeText(context, it, Toast.LENGTH_LONG).show()
    }
}