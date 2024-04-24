package com.appsdeviser.timetrackerpro.android.ui.core

import androidx.compose.ui.graphics.Color
import com.appsdeviser.core_common.constants.CategoryColorCommon
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LightBlue
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LightGreen
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LightIndigo
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LightOrange
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LightPink
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LightPrimaryColor
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LightRed
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LightViolet
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LightYellow

sealed class CategoryWithColor(val colorName: String = CategoryColorCommon.DEFAULT) {
    object Blue : CategoryWithColor(CategoryColorCommon.LIGHT_BLUE)
    object Green : CategoryWithColor(CategoryColorCommon.LIGHT_GREEN)
    object Red : CategoryWithColor(CategoryColorCommon.LIGHT_RED)
    object Pink : CategoryWithColor(CategoryColorCommon.LIGHT_PINK)
    object Orange : CategoryWithColor(CategoryColorCommon.LIGHT_ORANGE)
    object Violet : CategoryWithColor(CategoryColorCommon.LIGHT_VIOLET)
    object Yellow : CategoryWithColor(CategoryColorCommon.LIGHT_YELLOW)
    object Indigo : CategoryWithColor(CategoryColorCommon.LIGHT_INDIGO)
    object Default : CategoryWithColor(CategoryColorCommon.DEFAULT)

    fun getColor(): Color {
        return when(this){
            Orange -> LightOrange
            Blue -> LightBlue
            Green -> LightGreen
            Indigo -> LightIndigo
            Pink -> LightPink
            Red -> LightRed
            Violet -> LightViolet
            Yellow -> LightYellow
            Default -> LightPrimaryColor
        }
    }

    companion object {
        fun fromString(name: String): CategoryWithColor {
            return when (name) {
                CategoryColorCommon.LIGHT_ORANGE -> Orange
                CategoryColorCommon.LIGHT_BLUE -> Blue
                CategoryColorCommon.LIGHT_GREEN -> Green
                CategoryColorCommon.LIGHT_RED -> Red
                CategoryColorCommon.LIGHT_PINK -> Pink
                CategoryColorCommon.LIGHT_VIOLET -> Violet
                CategoryColorCommon.LIGHT_YELLOW -> Yellow
                CategoryColorCommon.LIGHT_INDIGO -> Indigo
                else -> Default
            }
        }
    }
}

