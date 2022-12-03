package com.inweapp.mavericksfundamentals.ui.list

import com.inweapp.mavericksfundamentals.model.domain.Filter
import com.inweapp.mavericksfundamentals.model.domain.Product
import javax.inject.Inject

/**
 * Created by sajon on 12/3/22
 * Copyright (c) 2022 syftet ltd. All rights reserved.
 * sajon@syftet.com
 * Last modified $file.lastModified
 */
class FilterGenerator @Inject constructor() {
    // todo test me!
    fun generate(products: List<Product>): Set<Filter> {
        return products.groupBy {
            it.category
        }.map {
            Filter(value = it.key, displayText = "${it.key} (${it.value.size})")
        }.toSet()
    }
}