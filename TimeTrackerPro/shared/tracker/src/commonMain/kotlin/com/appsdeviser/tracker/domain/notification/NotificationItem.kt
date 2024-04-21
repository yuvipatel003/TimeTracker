package com.appsdeviser.tracker.domain.notification


@kotlinx.serialization.Serializable
data class NotificationItem(
    val id: Long?,
    val image: String?,
    val title: String,
    val message: String
)
