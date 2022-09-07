package com.example.techshop_kotlin3.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.techshop_kotlin2.event.APIService2
import com.example.techshop_kotlin3.data.ProductDataSource2
import com.example.techshop_kotlin3.response.PageProductResponse
import com.example.techshop_kotlin3.response.ProductResponse2
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(private val apiService2: APIService2): ProductRespository {

    /*suspend fun getProductLaptop(): ProductResponse2?{
        kotlin.runCatching {
            val result = apiService2.getLapTop()
            if (result.isSuccessful){
                return result.body()
            }else{
                result.errorBody()
            }

        }.getOrElse {

        }

        return null
    }*/

    override suspend fun getProductLapTop(): ProductResponse2? {
        kotlin.runCatching {
            val result = apiService2.getLapTop()
            if (result.isSuccessful){
                return result.body()
            }else{
                result.errorBody()
            }

        }.getOrElse {

        }

        return null
    }

    override suspend fun getProductLapTop2(page: Int): PageProductResponse? {
        kotlin.runCatching {
            val result = apiService2.getLapTop2(page)
            if (result.isSuccessful){
                return result.body()
            }else{
                result.errorBody()
            }

        }.getOrElse {

        }

        return null
    }

    override fun getProduct() = Pager(
        config = PagingConfig(enablePlaceholders = false, pageSize = 20),
        pagingSourceFactory = {ProductDataSource2(apiService2)}
    ).liveData




}