package com.inweapp.mavericksfundamentals.hilt.services

import com.inweapp.mavericksfundamentals.model.network.NetworkProduct
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by sajon on 11/17/22
 * Copyright (c) 2022 syftet ltd. All rights reserved.
 * sajon@syftet.com
 * Last modified $file.lastModified
 */
interface ProductsService {
    @GET("products")
    suspend fun getAllProducts(): Response<List<NetworkProduct>>
}