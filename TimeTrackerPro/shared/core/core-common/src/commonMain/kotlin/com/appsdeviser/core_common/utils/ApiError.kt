package com.appsdeviser.core_common.utils
enum class ApiError {
    SERVICE_UNAVAILABLE,
    CLIENT_ERROR,
    SERVER_ERROR,
    UNKNOWN_ERROR,
    SERIALIZATION_ERROR,
    DATA_TRANSFORMATION_ERROR,
    INVALID_OR_UNSUPPORTED_LANGUAGE_CODE
}

class ApiException(val error: ApiError): Exception(
    "An error occurred during api call: $error"
)