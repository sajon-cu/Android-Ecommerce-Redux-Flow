package com.inweapp.mavericksfundamentals.redux.updater

import com.inweapp.mavericksfundamentals.redux.ApplicationState
import javax.inject.Inject

/**
 * Created by sajon on 12/4/22
 * Copyright (c) 2022 syftet ltd. All rights reserved.
 * sajon@syftet.com
 * Last modified $file.lastModified
 */
class UiProductFavoriteUpdater @Inject constructor() {
    fun productFavorited(productId: Int, currentState: ApplicationState): ApplicationState {
        var currentFavoriteIds = currentState.favoriteProductIds

        currentFavoriteIds = if(currentFavoriteIds.contains(productId)) {
            currentFavoriteIds - productId
        } else {
            currentFavoriteIds + productId
        }

        return currentState.copy(favoriteProductIds = currentFavoriteIds)
    }
}