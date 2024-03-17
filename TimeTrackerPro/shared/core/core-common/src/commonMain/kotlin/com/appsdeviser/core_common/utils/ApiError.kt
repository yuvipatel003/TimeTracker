package com.appsdeviser.core_common.utils

sealed interface ApiError: Error {
    enum class Network: ApiError {
        SERVICE_UNAVAILABLE,
        CLIENT_ERROR,
        SERVER_ERROR,
        UNKNOWN_ERROR,
        SERIALIZATION_ERROR,
        DATA_TRANSFORMATION_ERROR
    }
}