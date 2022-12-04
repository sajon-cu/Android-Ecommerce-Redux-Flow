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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by sajon on 11/17/22
 * Copyright (c) 2022 syftet ltd. All rights reserved.
 * sajon@syftet.com
 * Last modified $file.lastModified
 */
private const val TAG = "ProductListFragment"

@AndroidEntryPoint
class ProductListFragment : StoreBaseFragment<FragmentProductListBinding>() {
    override fun getBinding(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?
    ): FragmentProductListBinding {
        return FragmentProductListBinding.inflate(layoutInflater, viewGroup, false)
    }

    private val viewModel: ProductListViewModel by viewModels()

    @Inject
    lateinit var uiStateGenerator: ProductListFragmentUiStateGenerator


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productEpoxyController = ProductEpoxyController(viewModel)
        views.productRecyclerView.setController(productEpoxyController)

        combine(
            viewModel.uiProductListReducer.reduce(viewModel.store),
            viewModel.store.stateFlow.map { it.productFilterInfo }
        ) { uiProducts, productFilterInfo ->
            return@combine uiStateGenerator.generate(uiProducts, productFilterInfo)
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