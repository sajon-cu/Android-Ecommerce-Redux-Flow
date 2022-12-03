package com.inweapp.mavericksfundamentals

import android.annotation.SuppressLint
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import coil.load
import com.inweapp.mavericksfundamentals.databinding.EpoxyModelProductItemBinding
import com.inweapp.mavericksfundamentals.epoxy.ViewBindingKotlinModel
import com.inweapp.mavericksfundamentals.model.domain.Product
import com.inweapp.mavericksfundamentals.model.ui.UiProduct

/**
 * Created by sajon on 11/18/22
 * Copyright (c) 2022 syftet ltd. All rights reserved.
 * sajon@syftet.com
 * Last modified $file.lastModified
 */
data class ProductEpoxyModel(val product: UiProduct?): ViewBindingKotlinModel<EpoxyModelProductItemBinding>(R.layout.epoxy_model_product_item) {
    @SuppressLint("SetTextI18n")
    override fun EpoxyModelProductItemBinding.bind() {
        shimmerLayout.isVisible = product == null
        cardView.isInvisible = product == null

        product?.let {
            shimmerLayout.stopShimmer()
            productTitleTextView.text = product.product.title
            productDescriptionTextView.text = product.product.description
            productCategoryTextView.text = product.product.category
            productPriceTextView.text = product.product.price.toString()

            val imageRes = if(product.isFavorite) {
                R.drawable.ic_baseline_favorite_24
            } else {
                R.drawable.ic_round_favorite_border_24
            }

            favoriteImageView.setIconResource(imageRes)

            productImageViewLoadingProgressBar.isVisible = true
            productImageView.load(data = product.product.image) {
                listener { _, _ ->
                    productImageViewLoadingProgressBar.isVisible = false
                }
            }
        } ?: shimmerLayout.startShimmer()
    }
}