package com.appsdeviser.core_db.domain.feature

import com.appsdeviser.core_db.flows.CommonFlow

interface FeatureDataSource {

    fun getFeatures(): CommonFlow<List<FeatureItem>>

    suspend fun insertFeatures(list: List<FeatureItem>)

    suspend fun clearFeatures()
}