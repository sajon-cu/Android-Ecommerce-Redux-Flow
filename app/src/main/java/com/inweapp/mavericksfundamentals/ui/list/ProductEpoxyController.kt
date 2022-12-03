package com.inweapp.mavericksfundamentals.ui.list

import androidx.lifecycle.viewModelScope
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.TypedEpoxyController
import com.inweapp.mavericksfundamentals.model.domain.Filter
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
) : TypedEpoxyController<ProductListFragmentUiState>() {
    override fun buildModels(data: ProductListFragmentUiState?) {
        if (data == null) {
            repeat(7) {
                val epoxyId = it + 1
                UiProductEpoxyModel(
                    uiProduct = null,
                    onFavoriteIconClicked = ::onFavoriteIconClicked,
                    onUiProductClicked = ::onUiProductClicked
                ).id(epoxyId).addTo(this)
            }
            return
        }

        // TODO ADD FILTERS FORM UI STATE
        val uiFilteredModels = data.filters.map { uiFilter ->
            UiFilterProductEpoxyModel(
                uiFilter = uiFilter,
                onFilterSelected = ::onFilterSelected
            ).id(uiFilter.filter.value)
        }
        CarouselModel_().models(uiFilteredModels).id("filter").addTo(this)

        data.products.forEach { product ->
            UiProductEpoxyModel(
                uiProduct = product,
                onFavoriteIconClicked = ::onFavoriteIconClicked,
                onUiProductClicked = ::onUiProductClicked
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

    private fun onUiProductClicked(productId: Int) {
        viewModel.viewModelScope.launch {
            viewModel.store.update { currentState ->
                var currentExpandedIds = currentState.expandedProductIds
                currentExpandedIds = if(currentExpandedIds.contains(productId)) {
                    currentExpandedIds.filter { it != productId }.toSet()
                } else {
                    currentExpandedIds + setOf(productId)
                }
                return@update currentState.copy(expandedProductIds = currentExpandedIds)
            }
        }
    }

    private fun onFilterSelected(filter: Filter) {
        viewModel.viewModelScope.launch {
            viewModel.store.update { currentState ->
                return@update currentState.copy(
                    productFilterInfo = currentState.productFilterInfo.copy(selectedFilter = filter)
                )
            }
        }
    }
}