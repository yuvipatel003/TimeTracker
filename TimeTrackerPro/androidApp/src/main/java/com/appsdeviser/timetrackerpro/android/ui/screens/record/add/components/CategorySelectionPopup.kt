package com.appsdeviser.timetrackerpro.android.ui.screens.record.add.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.appsdeviser.core_common.presentation.UiCategory
import com.appsdeviser.core_common.utils.NONE
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing
import com.appsdeviser.timetrackerpro.android.ui.screens.category.components.CategoryDisplay
import com.appsdeviser.tracker.domain.category.CategoryItem

@Composable
fun CategorySelectionPopupForAddRecord(
    onSelect: (CategoryItem) -> Unit,
    items: List<CategoryItem>,
    selectedUiCategory: UiCategory,
    expanded: Boolean,
    onExpand: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    Column {
        Row(
            modifier = Modifier
                .clickable(onClick = { onExpand() })
                .padding(horizontal = spacing.spaceExtraSmall),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CategoryDisplay(
                category = selectedUiCategory
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = ""
            )
        }
        // Dropdown menu
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { onDismiss() }
        ) {
            items.forEachIndexed { index, item ->

                val uiCategory = UiCategory.allCategory.firstOrNull {
                    it.categoryType == item.type
                } ?: UiCategory.byType(NONE)

                val selected = UiCategory(uiCategory.drawableRes, item.name)

                CategoryDisplay(
                    category = selected,
                    modifier = Modifier.clickable {
                        onSelect(item)
                    }
                )
                if (index < items.size - 1) {
                    Spacer(
                        modifier = Modifier
                            .height(0.5.dp)
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.onPrimary)
                    )
                }
            }
        }
    }
}