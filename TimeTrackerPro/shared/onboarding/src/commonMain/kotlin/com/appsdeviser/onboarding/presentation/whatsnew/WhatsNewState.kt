package com.appsdeviser.onboarding.presentation.whatsnew

data class WhatsNewState(
    val list: List<WhatsNewItem> = emptyList(),
    val currentSelection: Int = 0,
    val event: WhatsNewEvent? = null,
)

data class WhatsNewItem(
    val image: String = "",
    val title: String = "",
    val description: String = ""
)