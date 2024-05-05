package com.appsdeviser.core_common.utils

inline fun <T> MutableList<T>.addAllIfNotExist(elements: Collection<T>) {
    for (element in elements) {
        if (!this.contains(element)) {
            this.add(element)
        }
    }
}

inline fun <T> MutableList<T>.removeIfExist(elements: Collection<T>) {
    for (element in elements) {
        if (!this.contains(element)) {
            this.remove(element)
        }
    }
}

fun <T> MutableList<T>.replace(oldItem: T, newItem: T) {
    val index = indexOf(oldItem)
    if (index != -1) {
        removeAt(index)
        add(index, newItem)
    }
}