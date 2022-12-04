package com.inweapp.mavericksfundamentals.ui.cart

import com.airbnb.epoxy.TypedEpoxyController
import com.inweapp.mavericksfundamentals.databinding.EpoxyModelCartProductItemBinding
import com.inweapp.mavericksfundamentals.epoxy.DividerEpoxyModel
import com.inweapp.mavericksfundamentals.epoxy.VerticalSpaceEpoxyModel
import com.inweapp.mavericksfundamentals.extensions.toPx
import com.inweapp.mavericksfundamentals.model.ui.UiProduct

/**
 * Created by sajon on 12/4/22
 * Copyright (c) 2022 syftet ltd. All rights reserved.
 * sajon@syftet.com
 * Last modified $file.lastModified
 */
class CartFragmentEpoxyController : TypedEpoxyController<CartFragment.UiState>() {
    override fun buildModels(data: CartFragment.UiState?) {
        when(data) {
            is CartFragment.UiState.Empty -> {
                EpoxyModelCartEmpty().id("empty_cart").addTo(this)
            }

            is CartFragment.UiState.NonEmpty -> {
                data.products.forEachIndexed { index, uiProduct ->
                    addVerticalStyling(index)

                    CartItemEpoxyModel(
                        uiProduct = uiProduct,
                        horizontalMargin = 16.toPx(),
                        onFavoriteClicked = {

                        },
                        onDeleteClicked = {

                        }
                    ).id(uiProduct.product.id).addTo(this)
                }
            }

            else -> throw RuntimeException("")
        }
    }

    private fun addVerticalStyling(index: Int) {
        VerticalSpaceEpoxyModel(8.toPx()).id("space_$index").addTo(this)

        if(index != 0) {
            DividerEpoxyModel(16.toPx()).id("divider_$index").addTo(this)
        }

        VerticalSpaceEpoxyModel(8.toPx()).id("space_$index").addTo(this)
    }
}