package com.appsdeviser.onboarding.domain.features

import com.appsdeviser.core_common.utils.ApiError
import com.appsdeviser.core_common.utils.Result
import com.appsdeviser.core_db.domain.feature.FeatureItem

interface FeaturesClient {

    suspend fun getFeatures(): Result<List<FeatureItem>, ApiError>
}