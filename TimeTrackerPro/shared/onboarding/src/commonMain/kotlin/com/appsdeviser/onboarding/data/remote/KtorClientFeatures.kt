package com.appsdeviser.onboarding.data.remote

import com.appsdeviser.core_api.remote.safeRequest
import com.appsdeviser.core_common.constants.NetworkConstants
import com.appsdeviser.core_db.domain.feature.FeatureItem
import com.appsdeviser.onboarding.domain.features.FeaturesClient
import io.ktor.client.HttpClient
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import io.ktor.http.takeFrom

class KtorClientFeatures(
    private val httpClient: HttpClient
): FeaturesClient {
    override suspend fun getFeatures(): List<FeatureItem> {
        return httpClient.safeRequest {
            method = HttpMethod.Get
            url.takeFrom(NetworkConstants.FEATURES_URL)
            contentType(ContentType.Application.Json)
        }
    }
}