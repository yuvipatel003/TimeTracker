package com.appsdeviser.core_common.constants

sealed class FeatureKey {
    data object Show_Home_Page : FeatureKey()
    data object Show_Settings_Page : FeatureKey()
}
