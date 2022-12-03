package com.inweapp.mavericksfundamentals

import android.annotation.SuppressLint
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import coil.load
import com.inweapp.mavericksfundamentals.databinding.EpoxyModelProductItemBinding
import com.inweapp.mavericksfundamentals.epoxy.ViewBindingKotlinModel
import com.inweapp.mavericksfundamentals.model.ui.UiProduct

/**
 * Created by sajon on 11/18/22
 * Copyright (c) 2022 syftet ltd. All rights reserved.
 * sajon@syftet.com
 * Last modified $file.lastModified
 */
data class UiProductEpoxyModel(
    val uiProduct: UiProduct?,
    val onFavoriteIconClicked: (Int) -> Unit
): ViewBindingKotlinModel<EpoxyModelProductItemBinding>(R.layout.epoxy_model_product_item) {
    @SuppressLint("SetTextI18n")
    override fun EpoxyModelProductItemBinding.bind() {
        shimmerLayout.isVisible = uiProduct == null
        cardView.isInvisible = uiProduct == null

        uiProduct?.let {
            shimmerLayout.stopShimmer()
            productTitleTextView.text = uiProduct.product.title
            productDescriptionTextView.text = uiProduct.product.description
            productCategoryTextView.text = uiProduct.product.category
            productPriceTextView.text = uiProduct.product.price.toString()

            val imageRes = if(uiProduct.isFavorite) {
                R.drawable.ic_baseline_favorite_24
            } else {
                R.drawable.ic_round_favorite_border_24
            }

            favoriteImageView.setIconResource(imageRes)
            favoriteImageView.setOnClickListener {
                onFavoriteIconClicked(uiProduct.product.id)
            }

            productImageViewLoadingProgressBar.isVisible = true
            productImageView.load(data = uiProduct.product.image) {
                listener { _, _ ->
                    productImageViewLoadingProgressBar.isVisible = false
                }
            }
        } ?: shimmerLayout.startShimmer()
    }
}