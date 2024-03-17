package com.appsdeviser.onboarding.domain.features

import com.appsdeviser.core_db.domain.feature.FeatureItem

interface FeaturesClient {

    suspend fun getFeatures(): List<FeatureItem>
}