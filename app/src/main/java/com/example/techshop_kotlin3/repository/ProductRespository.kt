package com.example.techshop_kotlin3.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.techshop_kotlin3.models.Product2
import com.example.techshop_kotlin3.response.PageProductResponse
import com.example.techshop_kotlin3.response.ProductResponse2

interface ProductRespository {
    suspend fun getProductLapTop() : ProductResponse2?
    suspend fun getProductLapTop2(page: Int) : PageProductResponse?
    fun getProduct(): LiveData<PagingData<Product2>>
}