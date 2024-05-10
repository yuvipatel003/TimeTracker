package com.appsdeviser.onboarding.data.remote

import com.appsdeviser.core_common.utils.Result
import com.appsdeviser.core_common.utils.error.ApiError
import com.appsdeviser.core_db.domain.feature.FeatureItem
import com.appsdeviser.core_db.domain.feature.FeatureList
import com.appsdeviser.onboarding.domain.features.FeaturesClient

class FakeFeaturesClient : FeaturesClient {

    companion object {
        val featureList = FeatureList(
            list = listOf(
                FeatureItem(
                    id = 1,
                    feature = "Feature 1",
                    enabled = false,
                    androidVersion = "1.0.0",
                    iOSVersion = "1.0.0"
                ),
                FeatureItem(
                    id = 2,
                    feature = "Feature 2",
                    enabled = false,
                    androidVersion = "1.1.0",
                    iOSVersion = "1.1.0"
                )
            )
        )
    }

    override suspend fun getFeatures(): Result<FeatureList, ApiError> {
        return Result.Success(
            featureList
        )
    }
}