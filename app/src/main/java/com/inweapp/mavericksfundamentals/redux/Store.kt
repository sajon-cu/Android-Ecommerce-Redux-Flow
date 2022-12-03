package com.inweapp.mavericksfundamentals.redux

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * Created by sajon on 11/17/22
 * Copyright (c) 2022 syftet ltd. All rights reserved.
 * sajon@syftet.com
 * Last modified $file.lastModified
 */
class Store<SS>(initialState: SS) {
    private val _stateFlow = MutableStateFlow(initialState)
    val stateFlow: StateFlow<SS> = _stateFlow

    private val mutex = Mutex()

    suspend fun update(updateBlock: (SS) -> SS) = mutex.withLock {
        val newState = updateBlock(_stateFlow.value)
        _stateFlow.value = newState
    }

    suspend fun read(readBlock: (SS) -> Unit) = mutex.withLock {
        readBlock(_stateFlow.value)
    }
}