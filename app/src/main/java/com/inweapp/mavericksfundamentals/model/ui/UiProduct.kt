package com.inweapp.mavericksfundamentals.model.ui

import com.inweapp.mavericksfundamentals.model.domain.Product

/**
 * Created by sajon on 11/18/22
 * Copyright (c) 2022 syftet ltd. All rights reserved.
 * sajon@syftet.com
 * Last modified $file.lastModified
 */
data class UiProduct(
    val product: Product,
    val isFavorite: Boolean = false,
    val isExpanded: Boolean = false
)