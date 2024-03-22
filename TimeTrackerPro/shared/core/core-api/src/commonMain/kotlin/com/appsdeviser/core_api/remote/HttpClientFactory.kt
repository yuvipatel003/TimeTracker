package com.appsdeviser.core_api.remote

import com.appsdeviser.core_common.utils.Result
import com.appsdeviser.core_common.utils.error.ApiError
import io.ktor.client.HttpClient
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.http.isSuccess
import io.ktor.utils.io.errors.IOException
import kotlinx.serialization.SerializationException

expect class HttpClientFactory {
    fun create(): HttpClient
}

suspend inline fun <reified T> HttpClient.safeRequest(
    apiCall: HttpRequestBuilder.() -> Unit,
): Result<T, ApiError> =
    try {
        val response = request { apiCall() }
        when {
            response.status.isSuccess() -> {
                try {
                    val result = response.body<T>()
                    Result.Success(result)
                } catch (e: Exception) {
                    Result.Error(ApiError.Network.UNKNOWN_ERROR)
                } catch (exception: NoTransformationFoundException) {
                    Result.Error(ApiError.Network.DATA_TRANSFORMATION_ERROR)
                }
            }

            else -> {
                when (response.status.value) {
                    500 -> Result.Error(ApiError.Network.SERVER_ERROR)
                    in 400..499 -> Result.Error(ApiError.Network.CLIENT_ERROR)
                    else -> Result.Error(ApiError.Network.UNKNOWN_ERROR)
                }
            }
        }
    } catch (exception: Exception) {
        Result.Error(ApiError.Network.UNKNOWN_ERROR)
    } catch (e: SerializationException) {
        Result.Error(ApiError.Network.SERIALIZATION_ERROR)
    } catch (e: IOException) {
        Result.Error(ApiError.Network.SERVICE_UNAVAILABLE)
    }