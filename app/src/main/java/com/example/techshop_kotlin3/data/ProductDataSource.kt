package com.example.techshop_kotlin3.data

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.techshop_kotlin3.models.Product2
import com.example.techshop_kotlin3.repository.ProductRepositoryImpl
import com.example.techshop_kotlin3.response.ProductResponse2
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ProductDataSource(
    private val coroutineScope: CoroutineScope,
    private val productRepositoryImpl: ProductRepositoryImpl
) :
    PageKeyedDataSource<Int, Product2>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Product2>
    ) {
        coroutineScope.launch {
            val page = productRepositoryImpl.getProductLapTop2(1)
            if (page != null) {
                Log.d("dataSource", "page" + page.toString())
                page.data?.data?.let {

                    callback.onResult(it, null, page.current_page?.plus(1))
                    Log.d("dataSource2", "page" + it.toString())
                }
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Product2>) {
        coroutineScope.launch {
            val page = productRepositoryImpl.getProductLapTop2(params.key)
            if (page != null) {
                page.data?.data?.let {

                    callback.onResult(it, page.current_page?.plus(1))
                }
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Product2>) {

    }


}



