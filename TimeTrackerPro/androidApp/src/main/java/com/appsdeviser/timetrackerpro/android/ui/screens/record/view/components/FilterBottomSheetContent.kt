package com.appsdeviser.timetrackerpro.android.ui.screens.record.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.appsdeviser.core_db.domain.showrecordpage.ShowRecordPageSettingItem
import com.appsdeviser.timetrackerpro.android.R
import com.appsdeviser.timetrackerpro.android.ui.core.components.CustomDatePicker
import com.appsdeviser.timetrackerpro.android.ui.core.displayAsDate
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing
import com.appsdeviser.timetrackerpro.android.ui.screens.onboarding.components.ActionButton
import com.appsdeviser.timetrackerpro.android.ui.screens.onboarding.components.OutlinedActionButton
import com.appsdeviser.timetrackerpro.android.ui.screens.record.add.components.AddRecordSelectCategory
import com.appsdeviser.timetrackerpro.android.ui.screens.settings.components.RecordSettingItem
import com.appsdeviser.tracker.domain.category.CategoryItem
import com.appsdeviser.tracker.presentation.record.view.FilterRecordState

@Composable
fun FilterBottomSheetContent(
    onCancelClick: () -> Unit,
    onApplyClick: (filterRecordState: FilterRecordState) -> Unit,
    filterRecordState: FilterRecordState?,
    listOfCategoryItem: List<CategoryItem>,
    recordPageSettingItem: ShowRecordPageSettingItem
) {
    val spacing = LocalSpacing.current
    var categoryId by remember {
        mutableStateOf(filterRecordState?.categoryId)
    }
    var startDate by remember {
        mutableStateOf(
            filterRecordState?.startDate
        )
    }
    var endDate by remember {
        mutableStateOf(
            filterRecordState?.endDate
        )
    }
    var isPaid by remember { mutableStateOf(filterRecordState?.isPaid) }

    AddRecordSelectCategory(
        selectedCategory = listOfCategoryItem.firstOrNull { it.id == categoryId },
        modifier = Modifier,
        onSelectCategoryItem = {
            categoryId = it.id
        },
        listOfCategory = listOfCategoryItem,
        verticalPaddingBetweenTitleAndContent = 0.dp
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = spacing.spaceMedium
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.start_date),
            style = MaterialTheme.typography.titleSmall
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = startDate?.displayAsDate(
                    recordPageSettingItem.dateFormat
                ) ?: "",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.width(spacing.spaceSmall))
            CustomDatePicker(
                onSelect = {
                    startDate = it
                },
                isStartDate = true,
                selectedDate = endDate ?: ""
            )
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = spacing.spaceMedium
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.end_date),
            style = MaterialTheme.typography.titleSmall
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = endDate?.displayAsDate(
                    recordPageSettingItem.dateFormat
                ) ?: "",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.width(spacing.spaceSmall))
            CustomDatePicker(
                onSelect = {
                    endDate = it
                },
                isStartDate = false,
                selectedDate = startDate ?: ""
            )
        }
    }

    RecordSettingItem(
        title = stringResource(R.string.paid),
        isEnabled = isPaid ?: false,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = spacing.spaceMedium),
        onChange = {
            isPaid = it
        }
    )

    Spacer(
        modifier = Modifier
            .padding(horizontal = spacing.spaceMedium)
            .height(1.dp)
            .background(MaterialTheme.colorScheme.onBackground)
    )
    /**
     * Action Button
     */
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = spacing.spaceSmall),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        OutlinedActionButton(
            text = stringResource(R.string.reset),
            onClick = {
                onCancelClick()
            },
            modifier = Modifier
                .weight(1f)
                .padding(spacing.spaceSmall)
        )
        ActionButton(
            text = "Apply",
            isEnabled = true,
            onClick = {
                onApplyClick(
                    FilterRecordState(
                        categoryId = categoryId,
                        isPaid = isPaid,
                        startDate = startDate,
                        endDate = endDate
                    )
                )
            },
            modifier = Modifier
                .weight(1f)
                .padding(spacing.spaceSmall)
        )
    }
}