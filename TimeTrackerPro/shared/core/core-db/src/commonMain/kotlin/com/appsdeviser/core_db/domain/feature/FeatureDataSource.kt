package com.appsdeviser.core_db.domain.feature

import com.appsdeviser.core_db.flows.CommonFlow

interface FeatureDataSource {

    fun getFeatures(): CommonFlow<List<FeatureItem>>

    suspend fun setFeature(item: FeatureItem)

    suspend fun clearFeatures()
}