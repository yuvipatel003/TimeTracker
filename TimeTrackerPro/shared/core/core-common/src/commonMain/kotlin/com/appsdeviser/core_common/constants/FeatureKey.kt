package com.appsdeviser.core_common.constants

sealed class FeatureKey {
    data object Show_Home_Page : FeatureKey()
    data object Show_Settings_Page : FeatureKey()
    data object Show_Add_Category_Page: FeatureKey()
    data object Show_Weekly_Target_Page: FeatureKey()
    data object Show_Notification: FeatureKey()
}
