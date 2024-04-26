package com.appsdeviser.timetrackerpro.android.ui.screens.category.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.appsdeviser.timetrackerpro.android.R
import com.appsdeviser.timetrackerpro.android.ui.core.components.TitleBar
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing

@Composable
fun ViewAllCategoryScreen(
    onBackClick: () -> Unit
) {
    val spacing = LocalSpacing.current

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
        ) {
        TitleBar(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.view_all_category),
            onBackClick = onBackClick
        )
    }
}