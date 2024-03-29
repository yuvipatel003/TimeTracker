package com.appsdeviser.core_db.featuremanager

import com.appsdeviser.AppConfig
import com.appsdeviser.core_common.constants.FeatureKey
import com.appsdeviser.core_common.utils.getAppVersionToInt
import com.appsdeviser.core_db.domain.feature.FeatureDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FeatureManager(
    featureDataSource: FeatureDataSource,
    private val appConfig: AppConfig
) {
    private val listOfFeatures = featureDataSource.getFeatures()
    private var features =
        addFeaturesWithStatus(appConfig.isAndroidPlatform, appConfig.applicationVersion)

    fun initialize() {
        features = addFeaturesWithStatus(appConfig.isAndroidPlatform, appConfig.applicationVersion)
    }

    private fun addFeaturesWithStatus(
        isAndroidPlatform: Boolean,
        currentAppVersion: String
    ): Flow<Map<String, Boolean>> {
        return listOfFeatures.map { list ->
            val featureMap = mutableMapOf<String, Boolean>()
            list.forEach { feature ->
                val enabled = if (isAndroidPlatform) {
                    currentAppVersion.getAppVersionToInt() >= feature.androidVersion.getAppVersionToInt()
                } else {
                    currentAppVersion.getAppVersionToInt() >= feature.iOSVersion.getAppVersionToInt()
                }
                featureMap[feature.feature] = enabled
            }
            featureMap
        }
    }

    fun isEnabled(featureKey: FeatureKey): Flow<Boolean> {
        return features.map {
            return@map it[featureKey.toString()] ?: false
        }
    }
}