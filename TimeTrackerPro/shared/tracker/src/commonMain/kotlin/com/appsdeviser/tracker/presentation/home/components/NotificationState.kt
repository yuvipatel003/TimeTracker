package com.appsdeviser.tracker.presentation.home.components

import com.appsdeviser.tracker.domain.notification.NotificationItem

data class NotificationState(
    val isNotificationOpen: Boolean = false,
    val currentPosition: Int = 0,
    val listOfNotification: List<NotificationItem> = notificationList
)

val notificationList = listOf(
    NotificationItem(
        id = 1,
        image = "https://upload.wikimedia.org/wikipedia/en/thumb/4/41/Flag_of_India.svg/1200px-Flag_of_India.svg.png",
        title = "Mark your Job as Favourite",
        message = "When you will mark your job as favourite you can start clock in and out from Home Screen"
    ),
    NotificationItem(
        id = 2,
        image = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d9/Flag_of_Canada_%28Pantone%29.svg/255px-Flag_of_Canada_%28Pantone%29.svg.png",
        title = "Your Weekly Goal",
        message = "Do you know, you can now set your weekly goal and track it as well."
    ),
    NotificationItem(
        id = 3,
        image = "https://upload.wikimedia.org/wikipedia/en/thumb/4/41/Flag_of_India.svg/1200px-Flag_of_India.svg.png",
        title = "Notification 3",
        message = "Message 3"
    ),
    NotificationItem(
        id = 4,
        image = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d9/Flag_of_Canada_%28Pantone%29.svg/255px-Flag_of_Canada_%28Pantone%29.svg.png",
        title = "Notification 4",
        message = "Message 4"
    ),
    NotificationItem(
        id = 5,
        image = null,
        title = "Mark your Job as Favourite",
        message = "When you will mark your job as favourite you can start clock in and out from Home Screen"
    ),
    NotificationItem(
        id = 6,
        image = null,
        title = "Your Weekly Goal",
        message = "Do you know, you can now set your weekly goal and track it as well."
    ),
)