package com.inweapp.mavericksfundamentals.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inweapp.mavericksfundamentals.hilt.repository.ProductRepository
import com.inweapp.mavericksfundamentals.model.domain.Product
import com.inweapp.mavericksfundamentals.redux.ApplicationState
import com.inweapp.mavericksfundamentals.redux.Store
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by sajon on 11/17/22
 * Copyright (c) 2022 syftet ltd. All rights reserved.
 * sajon@syftet.com
 * Last modified $file.lastModified
 */
@HiltViewModel
class ProductListViewModel @Inject constructor(
    val store: Store<ApplicationState>,
    private val productRepository: ProductRepository
) : ViewModel() {
    private val _loadingState = MutableStateFlow<Boolean>(false)
    val loadingState: StateFlow<Boolean> = _loadingState

    fun refreshProducts() = viewModelScope.launch {
        _loadingState.value = true

        val products: List<Product>? = productRepository.fetchAllProducts()

        store.update { applicationState ->
            return@update applicationState.copy(products = products?: emptyList())
        }

        _loadingState.value = false

        delay(4000)
        store.update {
            return@update it.copy(favoriteProductIds = setOf(1, 3, 4))
        }
    }
}