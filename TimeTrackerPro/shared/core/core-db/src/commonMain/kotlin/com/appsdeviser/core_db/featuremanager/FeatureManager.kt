package com.appsdeviser.core_db.featuremanager

import com.appsdeviser.AppConfig
import com.appsdeviser.core_common.constants.FeatureKey
import com.appsdeviser.core_common.utils.getAppVersionToInt
import com.appsdeviser.core_db.domain.feature.FeatureDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FeatureManager(
    private val featureDataSource: FeatureDataSource,
    private val appConfig: AppConfig
) {
    private val listOfFeatures = featureDataSource.getFeatures()

    fun isEnabled(featureKey: FeatureKey): Flow<Boolean> {
        return listOfFeatures.map { featureList ->
            val feature = featureList.firstOrNull {
                it.feature == featureKey.toString()
            }
            if (feature == null) {
                return@map false
            } else {
                return@map if (appConfig.isAndroidPlatform) {
                    feature.androidVersion.getAppVersionToInt() >= appConfig.applicationVersion.getAppVersionToInt()
                } else {
                    feature.iOSVersion.getAppVersionToInt() >= appConfig.applicationVersion.getAppVersionToInt()
                }
            }
        }
    }

    fun getFeaturesList() = listOfFeatures
}