package com.inweapp.mavericksfundamentals.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import com.inweapp.mavericksfundamentals.core.StoreBaseFragment
import com.inweapp.mavericksfundamentals.databinding.FragmentProductListBinding
import com.inweapp.mavericksfundamentals.model.ui.UiFilter
import com.inweapp.mavericksfundamentals.model.ui.UiProduct
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

/**
 * Created by sajon on 11/17/22
 * Copyright (c) 2022 syftet ltd. All rights reserved.
 * sajon@syftet.com
 * Last modified $file.lastModified
 */
private const val TAG = "ProductListFragment"

@AndroidEntryPoint
class ProductListFragment : StoreBaseFragment<FragmentProductListBinding>() {
    private val viewModel: ProductListViewModel by viewModels()
    override fun getBinding(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?
    ): FragmentProductListBinding {
        return FragmentProductListBinding.inflate(layoutInflater, viewGroup, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productEpoxyController = ProductEpoxyController(viewModel)
        views.productRecyclerView.setController(productEpoxyController)
        // productEpoxyController.setData(emptyList())

        combine(
            viewModel.store.stateFlow.map { it.products },
            viewModel.store.stateFlow.map { it.favoriteProductIds },
            viewModel.store.stateFlow.map { it.expandedProductIds },
            viewModel.store.stateFlow.map { it.productFilterInfo })
        { listOfProduct, setOfFavoriteIds, setOfExpandedIds, productFilterInfo ->
            val uiProducts = listOfProduct.map {
                UiProduct(
                    product = it,
                    isFavorite = setOfFavoriteIds.contains(it.id),
                    isExpanded = setOfExpandedIds.contains(it.id)
                )
            }

            val uiFilters = productFilterInfo.filters.map { filter ->
                UiFilter(
                    filter,
                    isSelected = productFilterInfo.selectedFilter?.equals(filter) == true
                )
            }.toSet()

            val filteredProducts = if (productFilterInfo.selectedFilter == null) {
                uiProducts
            } else {
                uiProducts.filter {
                    it.product.category == productFilterInfo.selectedFilter.value
                }
            }

            return@combine ProductListFragmentUiState(uiFilters, filteredProducts)
        }.distinctUntilChanged().asLiveData().observe(viewLifecycleOwner) { uiState ->
            productEpoxyController.setData(uiState)
        }

        /*viewModel.store.stateFlow.map {
            it.products
        }.distinctUntilChanged().asLiveData().observe(viewLifecycleOwner) { uiProducts ->
            productEpoxyController.setData(uiProducts)
        }

        viewModel.loadingState.asLiveData().observe(viewLifecycleOwner) {
            views.progressBar.isVisible = it
        }*/

        // viewModel.refreshProducts()
    }
}