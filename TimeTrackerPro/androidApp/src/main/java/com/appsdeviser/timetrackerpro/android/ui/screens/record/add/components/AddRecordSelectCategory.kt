package com.appsdeviser.timetrackerpro.android.ui.screens.record.add.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import com.appsdeviser.core_common.presentation.UiCategory
import com.appsdeviser.core_common.utils.NONE
import com.appsdeviser.timetrackerpro.android.R
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing
import com.appsdeviser.timetrackerpro.android.ui.screens.category.components.CategoryItemDisplay
import com.appsdeviser.tracker.domain.category.CategoryItem

@Composable
fun AddRecordSelectCategory(
    modifier: Modifier = Modifier,
    selectedCategory: CategoryItem? = null,
    listOfCategory: List<CategoryItem> = emptyList(),
    onSelectCategoryItem: (categoryItem: CategoryItem) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }
    val spacing = LocalSpacing.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = spacing.spaceMedium, vertical = spacing.spaceSmallMedium),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(id = R.string.category),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = spacing.spaceSmall)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.select),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .weight(3f)
            )
            Spacer(modifier = Modifier.width(spacing.spaceSmall))
            CategorySelectionPopupForAddRecord(
                items = listOfCategory,
                modifier = Modifier
                    .weight(1f)
                    .padding(spacing.spaceSmall),
                selectedUiCategory = UiCategory.byType(selectedCategory?.type ?: NONE),
                onSelect = { newCategory ->
                    onSelectCategoryItem(newCategory)
                    expanded = false
                },
                expanded = expanded,
                onExpand = {
                    expanded = true
                },
                onDismiss = {
                    expanded = false
                },
            )
        }

        selectedCategory?.let { categoryItem ->
            Spacer(modifier = Modifier.height(spacing.spaceSmall))
            CategoryItemDisplay(
                modifier = Modifier.clip(RoundedCornerShape(spacing.spaceSmallMedium)),
                categoryItem = categoryItem,
                showCategoryColumn = false,
                showFavouriteColumn = false,
                onFavourite = {

                })
        }
    }
}