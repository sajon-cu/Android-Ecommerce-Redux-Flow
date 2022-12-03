package com.inweapp.mavericksfundamentals.hilt.repository

import com.inweapp.mavericksfundamentals.hilt.services.ProductsService
import com.inweapp.mavericksfundamentals.model.domain.Product
import com.inweapp.mavericksfundamentals.model.mapper.ProductMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by sajon on 11/17/22
 * Copyright (c) 2022 syftet ltd. All rights reserved.
 * sajon@syftet.com
 * Last modified $file.lastModified
 */
class ProductRepository @Inject constructor(
    private val productsService: ProductsService,
    private val productMapper: ProductMapper
) {
    suspend fun fetchAllProducts(): List<Product>? = withContext(Dispatchers.IO) {
        return@withContext try {
            val response = productsService.getAllProducts()
            if(response.isSuccessful) response.body()?.let { networkProducts ->
                networkProducts.map { networkProduct ->
                    productMapper.buildFrom(networkProduct)
                }
            } else {
                emptyList()
            }
        } catch (ex: Exception) {
            println(ex)
            emptyList()
        }
    }
}