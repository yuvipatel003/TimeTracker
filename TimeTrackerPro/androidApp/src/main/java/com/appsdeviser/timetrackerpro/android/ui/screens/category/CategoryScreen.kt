package com.appsdeviser.timetrackerpro.android.ui.screens.category

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.appsdeviser.timetrackerpro.android.R
import com.appsdeviser.timetrackerpro.android.ui.core.components.SingleFloatingActionButton
import com.appsdeviser.timetrackerpro.android.ui.core.components.SwipeableContentView
import com.appsdeviser.timetrackerpro.android.ui.core.components.TitleBar
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing
import com.appsdeviser.timetrackerpro.android.ui.core.theme.TextColorBlack
import com.appsdeviser.timetrackerpro.android.ui.screens.category.components.AddCategoryContent
import com.appsdeviser.timetrackerpro.android.ui.screens.category.components.CategoryItemDisplay
import com.appsdeviser.tracker.presentation.category.CategoryEvent
import com.appsdeviser.tracker.presentation.category.CategoryState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(
    state: CategoryState,
    onEvent: (CategoryEvent) -> Unit,
    onBackClick: () -> Unit
) {
    val spacing = LocalSpacing.current
    var isSheetOpen by remember { mutableStateOf(false) }
    var bottomSheetState = rememberModalBottomSheetState()
    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
            ) {
                if (!isSheetOpen) {
                    SingleFloatingActionButton(
                        onClick = {
                            isSheetOpen = !isSheetOpen
                        },
                        imgVector = Icons.Default.Add,
                        title = stringResource(R.string.add_new_category)
                    )
                }
            }
        }
    ) { paddingValues ->

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            TitleBar(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(id = R.string.category),
                onBackClick = onBackClick
            )

            LazyColumn(
                modifier = Modifier
                    .padding(
                        horizontal = spacing.spaceExtraSmall,
                        vertical = spacing.spaceExtraSmall
                    )
                    .clip(RoundedCornerShape(spacing.spaceSmall))
            ) {
                items(state.listOfCategoryItem.size) {
                    val currentCategoryItem = state.listOfCategoryItem[it]
                    SwipeableContentView(
                        height = spacing.categoryItemHeight,
                        onEdit = {
                            onEvent(CategoryEvent.SelectCategory(currentCategoryItem))
                            isSheetOpen = true
                        },
                        onRemove = {
                            onEvent(CategoryEvent.RemoveCategory(currentCategoryItem))
                        },
                        content = {
                            CategoryItemDisplay(categoryItem = currentCategoryItem)
                        }
                    )
                    VerticalDivider(
                        modifier = Modifier
                            .height(1.dp)
                            .background(TextColorBlack)
                    )
                }
            }
        }
    }

    if (isSheetOpen) {
        ModalBottomSheet(
            sheetState = bottomSheetState,
            onDismissRequest = {
                isSheetOpen = !isSheetOpen
                onEvent(CategoryEvent.SelectCategory(null))
            }
        ) {
            AddCategoryContent(
                selectedItem = state.selectedCategory,
                onCancelClick = {
                    isSheetOpen = false
                    onEvent(CategoryEvent.SelectCategory(null))
                }, onAddClick = {
                    isSheetOpen = false
                    onEvent(CategoryEvent.AddCategory(it))
                }
            )
        }
    }
}