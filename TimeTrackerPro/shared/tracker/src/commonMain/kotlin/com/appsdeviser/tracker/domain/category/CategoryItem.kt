package com.appsdeviser.tracker.domain.category

@kotlinx.serialization.Serializable
data class CategoryItem(
    val id: Long?,
    val type: String,
    val name: String,
    val rate: Long,
    val favourite: Boolean,
    val color: String
)

@kotlinx.serialization.Serializable
data class CategoryList(
    val list: List<CategoryItem>
)