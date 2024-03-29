package com.appsdeviser.core_common.utils.error

sealed interface UiError : Error {
    enum class Notification : UiError {
        MISSING_REQUIRED_FIELD,
        INVALID_EMAIL,
        INVALID_USERNAME,
        INVALID_INPUT
    }
}
