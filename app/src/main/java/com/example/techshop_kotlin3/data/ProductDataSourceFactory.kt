package com.example.techshop_kotlin3.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.techshop_kotlin3.models.Product2
import com.example.techshop_kotlin3.repository.ProductRepositoryImpl
import com.example.techshop_kotlin3.response.ProductResponse2
import kotlinx.coroutines.CoroutineScope


class ProductDataSourceFactory (private val coroutineScope: CoroutineScope, private val productRepositoryImpl: ProductRepositoryImpl) : DataSource.Factory<Int, Product2>(){
    override fun create(): DataSource<Int, Product2> {
        return ProductDataSource(coroutineScope, productRepositoryImpl)
    }

}



