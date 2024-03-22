package com.appsdeviser.timetrackerpro.android.ui.core.error

import androidx.compose.runtime.Composable
import com.appsdeviser.core_common.utils.error.ApiError
import com.appsdeviser.core_common.utils.error.Error
import com.appsdeviser.core_common.utils.error.UiError
import com.appsdeviser.timetrackerpro.android.ui.core.error.dialog.ApiErrorDialog
import com.appsdeviser.timetrackerpro.android.ui.core.error.toast.UiErrorToast

@Composable
fun ErrorUI(
    onPositiveAction: (error: Error?) -> Unit,
    onNegativeAction: () -> Unit,
    error: Error?
) {
    when (error) {
        is ApiError -> {
            ApiErrorDialog(
                onPositiveAction = {
                    onPositiveAction(it as Error)
                } ,
                onNegativeAction = onNegativeAction,
                apiError = error)
        }

        is UiError -> {
            UiErrorToast (
                uiError = error
            )
        }

        else -> {}
    }
}