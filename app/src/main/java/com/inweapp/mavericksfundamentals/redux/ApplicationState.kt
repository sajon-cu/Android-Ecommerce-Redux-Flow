package com.inweapp.mavericksfundamentals.redux

import com.inweapp.mavericksfundamentals.model.domain.Filter
import com.inweapp.mavericksfundamentals.model.domain.Product

/**
 * Created by sajon on 11/17/22
 * Copyright (c) 2022 syftet ltd. All rights reserved.
 * sajon@syftet.com
 * Last modified $file.lastModified
 */
data class ApplicationState(
    val products: List<Product> = emptyList(),
    val productFilterInfo: ProductFilterInfo = ProductFilterInfo(),
    val favoriteProductIds: Set<Int> = emptySet(),
    val expandedProductIds: Set<Int> = emptySet()
) {
    data class ProductFilterInfo(
        val filters: Set<Filter> = emptySet(),
        val selectedFilter: Filter? = null
    )
}