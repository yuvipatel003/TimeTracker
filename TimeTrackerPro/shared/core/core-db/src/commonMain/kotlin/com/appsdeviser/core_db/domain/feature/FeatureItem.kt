package com.appsdeviser.core_db.domain.feature

@kotlinx.serialization.Serializable
data class FeatureItem(
    val id: Long?,
    val feature: String,
    val enabled: Boolean,
    val androidVersion: String,
    val iOSVersion: String
)

@kotlinx.serialization.Serializable
data class FeatureList(
    val list: List<FeatureItem>
)
