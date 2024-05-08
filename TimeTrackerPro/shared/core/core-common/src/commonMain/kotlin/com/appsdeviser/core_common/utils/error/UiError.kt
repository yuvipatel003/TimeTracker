package com.appsdeviser.core_common.utils.error

sealed interface UiError : Error {
    enum class Notification : UiError {
        MISSING_REQUIRED_FIELD,
        INVALID_EMAIL,
        INVALID_USERNAME,
        INVALID_INPUT,
        UPDATED,
        RECORD_ADDED,
        FAILED_TO_LOAD_MORE_DATA,
        FAVOURITE_CATEGORY_DELETE_WARNING,
        ADD_CATEGORY_WARNING
    }
}
