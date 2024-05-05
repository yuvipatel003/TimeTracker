package com.appsdeviser.timetrackerpro.android.ui.screens.category.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.appsdeviser.core_common.presentation.UiCategory
import com.appsdeviser.timetrackerpro.android.R
import com.appsdeviser.timetrackerpro.android.ui.core.CategoryWithColor
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing
import com.appsdeviser.timetrackerpro.android.ui.core.theme.PrimaryColor
import com.appsdeviser.timetrackerpro.android.ui.core.theme.TextColorBlack
import com.appsdeviser.tracker.domain.category.CategoryItem

@Composable
fun CategoryItemDisplay(
    categoryItem: CategoryItem,
    onFavourite: (isFavourite: Boolean) -> Unit,
    showFavouriteColumn: Boolean = true,
    showCategoryColumn: Boolean = true,
    showRateColumn: Boolean = true,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current

    Box(
        modifier = modifier
            .height(spacing.categoryItemHeight)
            .background(
                CategoryWithColor
                    .fromString(categoryItem.color)
                    .getColor()
            )
    ) {

        Row(
            modifier = Modifier
                .align(Alignment.CenterStart),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (showCategoryColumn) {
                CategoryDisplay(
                    category = UiCategory.byType(categoryItem.type),
                    modifier = Modifier.weight(2f),
                    textStyle = MaterialTheme.typography.titleSmall,
                    textFontWeight = FontWeight.Bold,
                    color = TextColorBlack,
                    paddingValue = spacing.spaceExtraSmall,
                )
            }
            Column(
                modifier = Modifier
                    .weight(3f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.organization_activity),
                    style = MaterialTheme.typography.bodySmall,
                    color = TextColorBlack
                )
                Spacer(
                    modifier = Modifier
                        .height(spacing.spaceSmall)
                )
                Text(
                    text = categoryItem.name,
                    style = MaterialTheme.typography.titleSmall,
                    color = TextColorBlack,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            if (showRateColumn) {
                Column(
                    modifier = Modifier.weight(1.5f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.rate_earning),
                        style = MaterialTheme.typography.bodySmall,
                        color = TextColorBlack
                    )
                    Spacer(
                        modifier = Modifier
                            .height(spacing.spaceSmall)
                    )
                    Text(
                        text = categoryItem.rate.toString(),
                        style = MaterialTheme.typography.titleSmall,
                        color = TextColorBlack,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            if (showFavouriteColumn) {
                Icon(
                    painter = painterResource(id = if (categoryItem.favourite) R.drawable.favourite_selected else R.drawable.favourite_not_selected),
                    contentDescription = "",
                    modifier = Modifier
                        .weight(0.75f)
                        .size(spacing.categoryIconSize)
                        .clickable {
                            onFavourite(!categoryItem.favourite)
                        },
                    tint = PrimaryColor
                )
            }
        }
    }
}