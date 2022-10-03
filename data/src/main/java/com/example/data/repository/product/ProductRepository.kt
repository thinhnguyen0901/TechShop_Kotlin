package com.example.data.repository.product

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.data.model.Product2
import com.example.data.model.Response
import kotlinx.coroutines.flow.Flow

interface ProductRepository {


    fun getProduct(): LiveData<PagingData<Product2>>
    fun getProductDB(): LiveData<PagingData<Product2>>

    suspend fun loadProducts(page: Int): Flow<Response<Product2>>

    fun loadProductsLive(page: Int): LiveData<Response<Product2>>

    suspend fun loadProductsSuspend(page: Int): Response<Product2>
}