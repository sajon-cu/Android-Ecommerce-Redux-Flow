package com.inweapp.mavericksfundamentals.model.mapper

import com.inweapp.mavericksfundamentals.model.domain.Product
import com.inweapp.mavericksfundamentals.model.network.NetworkProduct
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*
import javax.inject.Inject

/**
 * Created by sajon on 11/17/22
 * Copyright (c) 2022 syftet ltd. All rights reserved.
 * sajon@syftet.com
 * Last modified $file.lastModified
 */
class ProductMapper @Inject constructor() {
    fun buildFrom(networkProduct: NetworkProduct): Product {
        return Product(
            category = capitalize(networkProduct.category),
            description = networkProduct.description,
            id = networkProduct.id,
            image = networkProduct.image,
            price = BigDecimal(networkProduct.price).setScale(2, RoundingMode.HALF_UP),
            title = networkProduct.title,
            rating = Product.Rating(
                value = networkProduct.rating.rate,
                numberOfRatings = networkProduct.rating.count
            )
        )
    }

    private fun capitalize(sequence: String): String {
        return sequence.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
        }
    }
}