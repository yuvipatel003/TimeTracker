package com.appsdeviser.core_common.presentation

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}