package com.inweapp.mavericksfundamentals.redux.reducer

import com.inweapp.mavericksfundamentals.model.ui.UiFilter
import com.inweapp.mavericksfundamentals.model.ui.UiProduct
import com.inweapp.mavericksfundamentals.redux.ApplicationState
import com.inweapp.mavericksfundamentals.redux.Store
import com.inweapp.mavericksfundamentals.ui.list.ProductListFragmentUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by sajon on 12/4/22
 * Copyright (c) 2022 syftet ltd. All rights reserved.
 * sajon@syftet.com
 * Last modified $file.lastModified
 */
class UiProductListReducer @Inject constructor() {
    fun reduce(store: Store<ApplicationState>): Flow<List<UiProduct>> {
        return combine(
            store.stateFlow.map { it.products },
            store.stateFlow.map { it.favoriteProductIds },
            store.stateFlow.map { it.expandedProductIds },
            store.stateFlow.map { it.inCartProductIds },
        ) { listOfProduct, setOfFavoriteIds, setOfExpandedIds, inCartProductIds ->
            if(listOfProduct.isEmpty()) {
                return@combine emptyList<UiProduct>()
            }

            return@combine listOfProduct.map {
                UiProduct(
                    product = it,
                    isFavorite = setOfFavoriteIds.contains(it.id),
                    isExpanded = setOfExpandedIds.contains(it.id),
                    isInCart = inCartProductIds.contains(it.id)
                )
            }
        }


    }
}