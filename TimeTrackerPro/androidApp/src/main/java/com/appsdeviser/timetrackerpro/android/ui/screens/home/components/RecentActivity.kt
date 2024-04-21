package com.appsdeviser.timetrackerpro.android.ui.screens.home.components


import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun RecentActivity(
    modifier: Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        // Get recent activity here
        items(55) { pair ->
            Text(
                text = "This is Home Page" + pair.toString()
            )
        }
    }
}