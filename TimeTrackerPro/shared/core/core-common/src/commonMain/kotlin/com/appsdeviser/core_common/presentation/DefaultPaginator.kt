package com.appsdeviser.core_common.presentation

import com.appsdeviser.core_common.utils.Result
import com.appsdeviser.core_common.utils.error.Error
import com.appsdeviser.core_common.utils.error.UiError

class DefaultPaginator<Key, Item>(
    private val initialKey : Key,
    private inline val onLoadUpdated : (Boolean) -> Unit,
    private inline val onRequest : suspend (nextKey : Key) -> Result<List<Item>, Error>,
    private inline val getNextKey : suspend (List<Item>) -> Key,
    private inline val onError : suspend (Error) -> Unit,
    private inline val onSuccess : suspend (items: List<Item>, newKey: Key) -> Unit
): Paginator<Key, Item> {

    private var currentKey: Key = initialKey
    private var isMakingRequest = false

    override suspend fun loadNextItems() {
        if(isMakingRequest) {
            return
        }
        isMakingRequest = true
        onLoadUpdated(true)
        val result = onRequest(currentKey)
        isMakingRequest = false

        when(result) {
            is Result.Error -> {
                onError(UiError.Notification.FAILED_TO_LOAD_MORE_DATA)
                onLoadUpdated(false)
                return
            }
            is Result.Success -> {
                val items = result.data
                currentKey = getNextKey(items)
                onSuccess(items, currentKey)
                onLoadUpdated(false)
            }
        }
    }

    override fun reset() {
        currentKey = initialKey
    }

}