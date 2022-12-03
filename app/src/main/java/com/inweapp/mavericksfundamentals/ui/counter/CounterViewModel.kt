package com.inweapp.mavericksfundamentals.ui.counter

import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksViewModel

/**
 * Created by sajon on 11/17/22
 * Copyright (c) 2022 syftet ltd. All rights reserved.
 * sajon@syftet.com
 * Last modified $file.lastModified
 */
data class CounterState(val count: Int = 0): MavericksState

class CounterViewModel(state: CounterState) : MavericksViewModel<CounterState>(state) {
    fun incrementCount() {
        setState {
            copy(count = count + 1)
        }
    }
}