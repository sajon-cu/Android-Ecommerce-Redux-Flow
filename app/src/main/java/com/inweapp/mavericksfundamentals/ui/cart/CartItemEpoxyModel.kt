package com.inweapp.mavericksfundamentals.ui.cart

import android.view.ViewGroup
import androidx.annotation.Dimension
import androidx.annotation.Dimension.PX
import androidx.core.view.updateLayoutParams
import coil.load
import com.inweapp.mavericksfundamentals.R
import com.inweapp.mavericksfundamentals.databinding.EpoxyModelCartProductItemBinding
import com.inweapp.mavericksfundamentals.epoxy.ViewBindingKotlinModel
import com.inweapp.mavericksfundamentals.model.ui.UiProduct

/**
 * Created by sajon on 12/4/22
 * Copyright (c) 2022 syftet ltd. All rights reserved.
 * sajon@syftet.com
 * Last modified $file.lastModified
 */
class CartItemEpoxyModel(
    private val uiProduct: UiProduct,
    @Dimension(unit = PX) private val horizontalMargin: Int = 0,
    private val onFavoriteClicked: () -> Unit,
    private val onDeleteClicked: () -> Unit
) : ViewBindingKotlinModel<EpoxyModelCartProductItemBinding>(R.layout.epoxy_model_cart_product_item) {
    override fun EpoxyModelCartProductItemBinding.bind() {
        productTitleTextView.text = uiProduct.product.title

        // Favorite Icon
        val imageRes = if(uiProduct.isFavorite) {
            R.drawable.ic_baseline_favorite_24
        } else {
            R.drawable.ic_round_favorite_border_24
        }

        favoriteImageView.setIconResource(imageRes)
        favoriteImageView.setOnClickListener { onFavoriteClicked() }

        productImageView.load(data = uiProduct.product.image)

        root.updateLayoutParams<ViewGroup.MarginLayoutParams> {
            setMargins(horizontalMargin, 0, horizontalMargin, 0)
        }
    }
}