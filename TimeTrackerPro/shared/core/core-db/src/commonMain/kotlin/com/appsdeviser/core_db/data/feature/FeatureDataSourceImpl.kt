package com.appsdeviser.core_db.data.feature

import app.cash.sqldelight.coroutines.asFlow
import com.appsdeviser.core_common.utils.toLong
import com.appsdeviser.core_db.domain.feature.FeatureDataSource
import com.appsdeviser.core_db.domain.feature.FeatureItem
import com.appsdeviser.core_db.flows.CommonFlow
import com.appsdeviser.core_db.flows.toCommonFlow
import com.appsdeviser.core_db.sqldelight.TimeTrackerDatabase
import kotlinx.coroutines.flow.map

class FeatureDataSourceImpl(
    db: TimeTrackerDatabase
): FeatureDataSource {

    private val queries = db.timetrackerQueries

    override fun getFeatures(): CommonFlow<List<FeatureItem>> {
        return queries
            .getFeatures()
            .asFlow()
            .map {
                it.executeAsList().map { featureEntity ->
                    featureEntity.toFeatureItem()
                }
            }.toCommonFlow()
    }

    override suspend fun insertFeatures(list: List<FeatureItem>) {
        list.forEach { item ->
            queries.insertFeature(
                id = item.id,
                feature = item.feature,
                enabled = item.enabled.toLong(),
                androidVersion = item.androidVersion,
                iOSVersion = item.iOSVersion
            )
        }
    }

    override suspend fun clearFeatures() {
        queries.clearFeatures()
    }
}