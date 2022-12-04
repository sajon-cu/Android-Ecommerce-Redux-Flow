package com.inweapp.mavericksfundamentals.ui.list

import com.inweapp.mavericksfundamentals.model.domain.Filter
import com.inweapp.mavericksfundamentals.model.domain.Product
import com.inweapp.mavericksfundamentals.model.ui.UiFilter
import com.inweapp.mavericksfundamentals.model.ui.UiProduct
import com.inweapp.mavericksfundamentals.redux.ApplicationState
import javax.inject.Inject

/**
 * Created by sajon on 12/3/22
 * Copyright (c) 2022 syftet ltd. All rights reserved.
 * sajon@syftet.com
 * Last modified $file.lastModified
 */
class ProductListFragmentUiStateGenerator @Inject constructor() {
    fun generate(uiProducts: List<UiProduct>, filterInfo: ApplicationState.ProductFilterInfo): ProductListFragmentUiState {
        if(uiProducts.isEmpty()) {
            return ProductListFragmentUiState.Loading
        }

        val uiFilters = filterInfo.filters.map { filter ->
            UiFilter(
                filter = filter,
                isSelected = filterInfo.selectedFilter?.equals(filter) == true
            )
        }.toSet()

        val filterProducts = if(filterInfo.selectedFilter == null) {
            uiProducts
        } else {
            uiProducts.filter {
                it.product.category == filterInfo.selectedFilter.value
            }
        }

        return ProductListFragmentUiState.Success(
            filters = uiFilters,
            products = filterProducts
        )
    }
}