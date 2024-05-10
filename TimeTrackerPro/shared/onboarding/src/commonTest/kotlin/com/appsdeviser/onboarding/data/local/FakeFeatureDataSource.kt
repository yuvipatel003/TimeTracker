package com.appsdeviser.onboarding.data.local

import com.appsdeviser.core_db.domain.feature.FeatureDataSource
import com.appsdeviser.core_db.domain.feature.FeatureItem
import com.appsdeviser.core_db.flows.CommonFlow
import com.appsdeviser.core_db.flows.toCommonFlow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeFeatureDataSource : FeatureDataSource {

    private val _data = MutableStateFlow<List<FeatureItem>>(emptyList())

    override fun getFeatures(): CommonFlow<List<FeatureItem>> {
        return _data.toCommonFlow()
    }

    override suspend fun insertFeatures(list: List<FeatureItem>) {
        _data.value += list
    }

    override suspend fun clearFeatures() {
        _data.value = emptyList()
    }
}