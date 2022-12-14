package com.inweapp.mavericksfundamentals.ui.list

import com.inweapp.mavericksfundamentals.model.domain.Filter
import com.inweapp.mavericksfundamentals.model.domain.Product
import com.inweapp.mavericksfundamentals.model.ui.UiFilter
import com.inweapp.mavericksfundamentals.model.ui.UiProduct

/**
 * Created by sajon on 12/3/22
 * Copyright (c) 2022 syftet ltd. All rights reserved.
 * sajon@syftet.com
 * Last modified $file.lastModified
 */
sealed interface ProductListFragmentUiState {
    data class Success(
        val filters: Set<UiFilter>,
        val products: List<UiProduct>
    ) : ProductListFragmentUiState

    object Loading : ProductListFragmentUiState
}