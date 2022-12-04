package com.inweapp.mavericksfundamentals.ui.cart

import androidx.lifecycle.ViewModel
import com.inweapp.mavericksfundamentals.redux.ApplicationState
import com.inweapp.mavericksfundamentals.redux.Store
import com.inweapp.mavericksfundamentals.redux.reducer.UiProductListReducer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by sajon on 12/4/22
 * Copyright (c) 2022 syftet ltd. All rights reserved.
 * sajon@syftet.com
 * Last modified $file.lastModified
 */
@HiltViewModel
class CartFragmentViewModel @Inject constructor(
    val store: Store<ApplicationState>,
    val uiProductListReducer: UiProductListReducer
) : ViewModel() {
    fun show() {
        println("Hello, world")
    }
}