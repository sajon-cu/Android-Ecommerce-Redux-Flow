package com.inweapp.mavericksfundamentals.model.domain

import java.math.BigDecimal

/**
 * Created by sajon on 11/17/22
 * Copyright (c) 2022 syftet ltd. All rights reserved.
 * sajon@syftet.com
 * Last modified $file.lastModified
 */
data class Product(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: BigDecimal,
    val title: String,
    val rating: Rating
) {
    data class Rating(
        val value: Double,
        val numberOfRatings: Int
    )
}