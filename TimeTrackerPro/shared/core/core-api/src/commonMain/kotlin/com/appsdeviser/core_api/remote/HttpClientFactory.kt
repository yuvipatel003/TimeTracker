package com.appsdeviser.core_api.remote

import com.appsdeviser.core_common.utils.ApiError
import com.appsdeviser.core_common.utils.ApiException
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
): T =
    try {
        val response = request { apiCall() }
        when {
            response.status.isSuccess() -> {
                try {
                    val result = response.body<T>()
                    result
                } catch (e: Exception) {
                    throw ApiException(ApiError.SERVER_ERROR)
                } catch (exception: NoTransformationFoundException) {
                    throw (ApiException(ApiError.DATA_TRANSFORMATION_ERROR))
                }
            }

            else -> {
                when (response.status.value) {
                    500 -> throw ApiException(ApiError.SERVER_ERROR)
                    in 400..499 -> throw ApiException(ApiError.CLIENT_ERROR)
                    else -> throw ApiException(ApiError.UNKNOWN_ERROR)
                }
            }
        }
    } catch (exception: Exception) {
        throw ApiException(ApiError.UNKNOWN_ERROR)
    } catch (e: SerializationException) {
        throw ApiException(ApiError.SERIALIZATION_ERROR)
    } catch (e: IOException) {
        throw ApiException(ApiError.SERVICE_UNAVAILABLE)
    }