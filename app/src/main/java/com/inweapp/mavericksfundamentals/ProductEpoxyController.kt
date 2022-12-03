package com.inweapp.mavericksfundamentals

import com.airbnb.epoxy.TypedEpoxyController
import com.inweapp.mavericksfundamentals.model.domain.Product
import com.inweapp.mavericksfundamentals.model.ui.UiProduct

/**
 * Created by sajon on 11/18/22
 * Copyright (c) 2022 syftet ltd. All rights reserved.
 * sajon@syftet.com
 * Last modified $file.lastModified
 */
class ProductEpoxyController: TypedEpoxyController<List<UiProduct>>() {
    override fun buildModels(data: List<UiProduct>?) {
        if(data.isNullOrEmpty()) {
            repeat(7) {
                val epoxyId = it + 1
                ProductEpoxyModel(null).id(epoxyId).addTo(this)
            }
            return
        }

        data.forEach { product ->
            ProductEpoxyModel(product).id(product.product.id).addTo(this)
        }
    }
}