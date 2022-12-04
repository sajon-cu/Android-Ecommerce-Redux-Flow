package com.inweapp.mavericksfundamentals.ui.cart

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import com.inweapp.mavericksfundamentals.core.StoreBaseFragment
import com.inweapp.mavericksfundamentals.databinding.FragmentCartBinding
import com.inweapp.mavericksfundamentals.model.ui.UiProduct
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

private const val TAG = "CartFragment"
/**
 * Created by sajon on 11/17/22
 * Copyright (c) 2022 syftet ltd. All rights reserved.
 * sajon@syftet.com
 * Last modified $file.lastModified
 */
@AndroidEntryPoint
class CartFragment : StoreBaseFragment<FragmentCartBinding>() {
    override fun getBinding(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?
    ): FragmentCartBinding {
        return FragmentCartBinding.inflate(layoutInflater, viewGroup, false)
    }

    private val viewModel: CartFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cartFragmentEpoxyController = CartFragmentEpoxyController()
        views.productRecyclerView.setController(cartFragmentEpoxyController)

        viewModel.uiProductListReducer.reduce(store = viewModel.store).map { uiProducts ->
            uiProducts.filter { it.isInCart }
        }.distinctUntilChanged().asLiveData().observe(viewLifecycleOwner) { uiProducts ->
            val viewState = if(uiProducts.isEmpty()) {
                UiState.Empty
            } else {
                UiState.NonEmpty(uiProducts)
            }
            cartFragmentEpoxyController.setData(viewState)
        }
    }

    sealed interface UiState {
        object Empty: UiState
        data class NonEmpty(val products: List<UiProduct>): UiState
    }
}