package com.inweapp.mavericksfundamentals

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.airbnb.epoxy.TypedEpoxyController
import com.inweapp.mavericksfundamentals.model.ui.UiProduct
import com.inweapp.mavericksfundamentals.ui.list.ProductListViewModel
import kotlinx.coroutines.launch

/**
 * Created by sajon on 11/18/22
 * Copyright (c) 2022 syftet ltd. All rights reserved.
 * sajon@syftet.com
 * Last modified $file.lastModified
 */
private const val TAG = "ProductEpoxyController"

class ProductEpoxyController(
    private val viewModel: ProductListViewModel
) : TypedEpoxyController<List<UiProduct>>() {
    override fun buildModels(data: List<UiProduct>?) {
        if (data.isNullOrEmpty()) {
            repeat(7) {
                val epoxyId = it + 1
                UiProductEpoxyModel(
                    uiProduct = null,
                    onFavoriteIconClicked = ::onFavoriteIconClicked
                ).id(epoxyId).addTo(this)
            }
            return
        }

        data.forEach { product ->
            UiProductEpoxyModel(
                uiProduct = product,
                onFavoriteIconClicked = ::onFavoriteIconClicked
            ).id(product.product.id).addTo(this)
        }
    }

    private fun onFavoriteIconClicked(selectedProductId: Int) {
        viewModel.viewModelScope.launch {
            viewModel.store.update { currentState ->
                var currentFavoriteIds = currentState.favoriteProductIds
                currentFavoriteIds = if(currentFavoriteIds.contains(selectedProductId)) {
                    currentFavoriteIds.filter { it != selectedProductId }.toSet()
                } else {
                    currentFavoriteIds + setOf(selectedProductId)
                }
                return@update currentState.copy(favoriteProductIds = currentFavoriteIds)
            }
        }
    }
}