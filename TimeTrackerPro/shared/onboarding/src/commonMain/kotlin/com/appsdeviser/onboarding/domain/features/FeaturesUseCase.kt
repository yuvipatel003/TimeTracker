package com.appsdeviser.onboarding.domain.features

import com.appsdeviser.core_common.utils.ApiException
import com.appsdeviser.core_common.utils.Result
import com.appsdeviser.core_db.domain.feature.FeatureDataSource
import com.appsdeviser.core_db.domain.feature.FeatureItem

class FeaturesUseCase(
    private val featuresClient: FeaturesClient,
    private val featureDataSource: FeatureDataSource
) {
    suspend fun invoke (): Result<List<FeatureItem>> {
        return try {
            val features = featuresClient.getFeatures()
            features.forEach { singleFeature ->
                featureDataSource.setFeature(singleFeature)
            }
            Result.Success(features)
        } catch (e: ApiException) {
            e.printStackTrace()
            Result.Error(e)
        }
    }
}