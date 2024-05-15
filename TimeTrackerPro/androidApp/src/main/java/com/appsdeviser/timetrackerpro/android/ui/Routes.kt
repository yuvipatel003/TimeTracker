package com.appsdeviser.timetrackerpro.android.ui

import kotlinx.serialization.Serializable

object Routes {

    @Serializable
    object Splash

    @Serializable
    object Home

    @Serializable
    object Onboarding

    @Serializable
    object WhatsNew

    @Serializable
    object Category

    @Serializable
    data class AddRecord(
        val recordId: Long = -1L,
        val categoryId: Long = -1L
    )

    @Serializable
    object ViewAllRecord

    @Serializable
    object Settings
}