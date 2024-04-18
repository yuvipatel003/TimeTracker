package com.appsdeviser.core_db.domain.category

@kotlinx.serialization.Serializable
data class CategoryItem(
    val id: Long?,
    val type: String,
    val name: String
)

@kotlinx.serialization.Serializable
data class CategoryList(
    val list: List<CategoryItem>
)