package com.appsdeviser.onboarding.domain.features

import com.appsdeviser.core_common.utils.Result
import com.appsdeviser.core_common.utils.error.ApiError
import com.appsdeviser.core_db.domain.feature.FeatureList

interface FeaturesClient {

    suspend fun getFeatures(): Result<FeatureList, ApiError>
}