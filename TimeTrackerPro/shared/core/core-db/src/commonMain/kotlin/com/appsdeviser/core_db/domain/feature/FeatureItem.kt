package com.appsdeviser.core_db.domain.feature

data class FeatureItem(
    val id: Long?,
    val feature: String,
    val enabled: Boolean,
    val androidVersion: String,
    val iOSVersion: String
)
