package com.appsdeviser.core_db.data.feature

import com.appsdeviser.core_db.domain.feature.FeatureItem
import database.FeatureEntity

fun FeatureEntity.toFeatureItem(): FeatureItem {
    return FeatureItem(
        id = this.id,
        feature = this.feature,
        enabled = this.enabled.equals(1),
        androidVersion = this.androidVersion,
        iOSVersion = this.iOSVersion
    )
}