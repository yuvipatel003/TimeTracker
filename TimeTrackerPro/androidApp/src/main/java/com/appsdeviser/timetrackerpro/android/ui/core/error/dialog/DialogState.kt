package com.appsdeviser.timetrackerpro.android.ui.core.error.dialog

data class DialogState(
    val title: String = "No Title",
    val description: String = "No Description",
    val positiveButtonText: String = "OK",
    val negativeButtonText: String? = null,
    val isCancelable: Boolean = true,
    val priority: DialogPriority = DialogPriority.UNKNOWN
)

sealed class DialogPriority(val priority: Int) {
    data object HIGH: DialogPriority(1)
    data object MEDIUM: DialogPriority(2)
    data object LOW: DialogPriority(3)
    data object UNKNOWN: DialogPriority(1000)
}
