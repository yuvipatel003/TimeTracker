package com.appsdeviser.core_db.flows

import kotlinx.coroutines.DisposableHandle

fun interface DisposableHandle : DisposableHandle
/**
 * Line 5 will cover following code
 *
 * interface DisposableHandle: DisposableHandle
 *
 * fun DisposableHandle(block: () -> Unit) : DisposableHandle {
 *     return object: DisposableHandle {
 *         override fun dispose() {
 *             block()
 *         }
 *     }
 * }
 */