package com.inweapp.mavericksfundamentals.redux.updater

import android.app.Application
import com.inweapp.mavericksfundamentals.redux.ApplicationState
import javax.inject.Inject

/**
 * Created by sajon on 12/4/22
 * Copyright (c) 2022 syftet ltd. All rights reserved.
 * sajon@syftet.com
 * Last modified $file.lastModified
 */
class UiProductInCartUpdater @Inject constructor() {
    fun update(productId: Int, currentState: ApplicationState): ApplicationState {
        var currentInCartIds = currentState.inCartProductIds
        currentInCartIds = if(currentInCartIds.contains(productId)) {
            currentInCartIds - productId
        } else {
            currentInCartIds + productId
        }
        return currentState.copy(inCartProductIds = currentInCartIds)
    }
}