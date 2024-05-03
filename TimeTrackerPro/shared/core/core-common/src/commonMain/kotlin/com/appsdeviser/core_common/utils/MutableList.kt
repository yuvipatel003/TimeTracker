package com.appsdeviser.core_common.utils

inline fun <T> MutableList<T>.addAllIfNotExist(elements: Collection<T>) {
    for (element in elements) {
        if (!this.contains(element)) {
            this.add(element)
        }
    }
}