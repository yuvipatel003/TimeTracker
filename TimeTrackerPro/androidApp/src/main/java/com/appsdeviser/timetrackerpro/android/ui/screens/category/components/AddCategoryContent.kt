package com.appsdeviser.timetrackerpro.android.ui.screens.category.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.appsdeviser.core_common.constants.CategoryColorCommon
import com.appsdeviser.core_common.presentation.UiCategory
import com.appsdeviser.core_common.utils.NONE
import com.appsdeviser.timetrackerpro.android.R
import com.appsdeviser.timetrackerpro.android.ui.core.CategoryWithColor
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing
import com.appsdeviser.timetrackerpro.android.ui.core.theme.TextColorBlack
import com.appsdeviser.timetrackerpro.android.ui.screens.onboarding.components.ActionButton
import com.appsdeviser.timetrackerpro.android.ui.screens.onboarding.components.OutlinedActionButton
import com.appsdeviser.tracker.domain.category.CategoryItem

@Composable
fun ColumnScope.AddCategoryContent(
    selectedItem: CategoryItem?,
    onCancelClick: () -> Unit,
    onAddClick: (categoryItem: CategoryItem) -> Unit,
    listOfExistingCategory: List<CategoryItem>
) {
    val spacing = LocalSpacing.current
    var selectedUiCategory by remember {

        mutableStateOf(UiCategory.byType(selectedItem?.type ?: NONE))
    }
    var name by remember { mutableStateOf(selectedItem?.name ?: "") }
    var selectedColorIndex by remember {
        mutableStateOf(
            CategoryColorCommon.listOfCategoryColors.indexOf(
                selectedItem?.color ?: -1
            )
        )
    }
    var rate by remember { mutableStateOf((selectedItem?.rate ?: "").toString()) }
    var expanded by remember { mutableStateOf(false) }

    /**
     * Category
     */
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = spacing.spaceSmall),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.select_category),
            modifier = Modifier
                .weight(1f)
                .padding(spacing.spaceSmall),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
        CategorySelectionPopup(
            items = UiCategory.allCategory,
            modifier = Modifier
                .weight(1f)
                .padding(spacing.spaceSmall),
            selectedUiCategory = selectedUiCategory,
            onSelect = { newCategory ->
                selectedUiCategory = newCategory
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

    /**
     * Company Name/ Activity Name
     */
    TextField(
        value = name,
        onValueChange = {
            name = it
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = spacing.spaceSmallMedium, vertical = spacing.spaceExtraSmall)
            .clip(RoundedCornerShape(spacing.spaceMedium)),
        label = {
            Text(
                text = stringResource(R.string.organization_name_or_activity_detail),
                style = MaterialTheme.typography.titleSmall
            )
        },
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            color = TextColorBlack
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        )
    )

    /**
     * Hourly Rate
     */

    TextField(
        value = rate,
        onValueChange = {
            rate = it
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = spacing.spaceSmallMedium, vertical = spacing.spaceExtraSmall)
            .clip(RoundedCornerShape(spacing.spaceMedium)),
        label = {
            Text(
                text = stringResource(R.string.hourly_rate_earning),
                style = MaterialTheme.typography.titleSmall
            )
        },
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            color = TextColorBlack
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal,
            imeAction = ImeAction.Done
        )
    )

    /**
     * Select CategoryColor
     */
    Text(
        text = stringResource(R.string.select_category_color),
        modifier = Modifier
            .padding(spacing.spaceMedium),
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Bold
    )
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = spacing.spaceSmall, horizontal = spacing.spaceMedium),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val listOfCategoryColors = CategoryColorCommon.listOfCategoryColors
        items(listOfCategoryColors.size) {
            val categoryColor = CategoryWithColor.fromString(listOfCategoryColors[it]).getColor()
            CategoryColorCircle(
                color = categoryColor,
                isSelected = selectedColorIndex == it,
                size = spacing.categoryIconSize,
                modifier = Modifier
                    .padding(horizontal = spacing.spaceSmall)
                    .weight(1f)
                    .clickable {
                        selectedColorIndex = it
                    }
            )
        }

    }

    /**
     * Action Button
     */
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = spacing.spaceSmall),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        OutlinedActionButton(
            text = stringResource(R.string.cancel),
            onClick = {
                onCancelClick()
            },
            modifier = Modifier
                .weight(1f)
                .padding(spacing.spaceSmall)
        )
        ActionButton(
            text = if (selectedItem != null) stringResource(R.string.update) else stringResource(R.string.add),
            isEnabled = name.isNotEmpty() && selectedColorIndex != -1 && rate.isNotEmpty(),
            onClick = {
                onAddClick(
                    CategoryItem(
                        id = selectedItem?.id,
                        type = selectedUiCategory.categoryType,
                        name = name,
                        rate = rate.toDouble(),
                        favourite = selectedItem?.favourite ?: listOfExistingCategory.isEmpty(),
                        color = CategoryColorCommon.listOfCategoryColors[selectedColorIndex]
                    )
                )
            },
            modifier = Modifier
                .weight(1f)
                .padding(spacing.spaceSmall)
        )
    }
}