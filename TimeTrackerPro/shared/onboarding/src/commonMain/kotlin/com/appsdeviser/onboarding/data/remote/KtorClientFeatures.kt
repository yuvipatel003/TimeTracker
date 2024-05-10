package com.appsdeviser.onboarding.data.remote

import com.appsdeviser.core_api.remote.safeRequest
import com.appsdeviser.core_common.constants.NetworkConstants
import com.appsdeviser.core_common.utils.Result
import com.appsdeviser.core_common.utils.error.ApiError
import com.appsdeviser.core_db.domain.feature.FeatureItem
import com.appsdeviser.core_db.domain.feature.FeatureList
import com.appsdeviser.onboarding.domain.features.FeaturesClient
import io.ktor.client.HttpClient
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import io.ktor.http.takeFrom

class KtorClientFeatures(
    private val httpClient: HttpClient
) : FeaturesClient {
    override suspend fun getFeatures(): Result<FeatureList, ApiError> {
//        return httpClient.safeRequest {
//            method = HttpMethod.Get
//            url.takeFrom(NetworkConstants.FEATURES_URL)
//            contentType(ContentType.Application.Json)
//        }
        return Result.Success(
            FeatureList(
                list = listOf(
                    FeatureItem(
                        id = 0,
                        feature = "Show_Home_Page",
                        enabled = true,
                        androidVersion = "1.0.0",
                        iOSVersion = "1.0.0"
                    ),
                    FeatureItem(
                        id = 1,
                        feature = "Show_Add_Category_Page",
                        enabled = true,
                        androidVersion = "1.0.0",
                        iOSVersion = "1.0.0"
                    ),
                    FeatureItem(
                        id = 2,
                        feature = "Show_Settings_Page",
                        enabled = true,
                        androidVersion = "1.1.0",
                        iOSVersion = "1.1.0"
                    ),
                    FeatureItem(
                        id = 3,
                        feature = "Show_Notification",
                        enabled = true,
                        androidVersion = "1.2.0",
                        iOSVersion = "1.2.0"
                    ),
                    FeatureItem(
                        id = 4,
                        feature = "Show_Weekly_Target_Page",
                        enabled = true,
                        androidVersion = "2.0.0",
                        iOSVersion = "2.0.0"
                    )
                )
            )
        )
    }
}