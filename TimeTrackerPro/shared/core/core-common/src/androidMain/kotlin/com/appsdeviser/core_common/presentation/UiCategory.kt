package com.appsdeviser.core_common.presentation

import androidx.annotation.DrawableRes
import com.appsdeviser.core_common.R
import com.appsdeviser.core_common.utils.COMMUNITY_SERVICE
import com.appsdeviser.core_common.utils.ENTERTAINMENT
import com.appsdeviser.core_common.utils.EXERCISE
import com.appsdeviser.core_common.utils.JOB
import com.appsdeviser.core_common.utils.NONE
import com.appsdeviser.core_common.utils.SPORT
import com.appsdeviser.core_common.utils.STUDY
import com.appsdeviser.core_common.utils.VOLUNTEER
import com.appsdeviser.core_common.utils.listOfCategory

actual class UiCategory(
    @DrawableRes val drawableRes: Int,
    actual val categoryType: String
) {
    actual companion object {
        actual fun byType(type: String): UiCategory {
            return allCategory.firstOrNull {
                it.categoryType == type
            } ?: byType(NONE)
        }

        actual val allCategory: List<UiCategory>
            get() = listOfCategory.map {
                UiCategory(
                    categoryType = it,
                    drawableRes = when (it) {
                        STUDY -> R.drawable.study
                        JOB -> R.drawable.job
                        VOLUNTEER -> R.drawable.volunteer
                        COMMUNITY_SERVICE -> R.drawable.community_service
                        SPORT -> R.drawable.sport
                        EXERCISE -> R.drawable.exercise
                        ENTERTAINMENT -> R.drawable.entertainment
                        else -> {
                            R.drawable.none
                        }
                    }
                )
            }

    }
}