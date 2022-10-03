package com.example.data.repository.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.data.api.event.APIService2
import com.example.data.model.Product2
import com.example.data.model.Response
import com.example.data.room.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(
    private val apiService2: APIService2,
    private val appDatabase: AppDatabase,
) : ProductRepository {


    override fun getProduct() = Pager(
        config = PagingConfig(enablePlaceholders = false, pageSize = 20),
        pagingSourceFactory = { ProductDataSource2(apiService2, appDatabase) }
    ).liveData

    override fun getProductDB() = Pager(
        config = PagingConfig(enablePlaceholders = false, pageSize = 20),
        pagingSourceFactory = { appDatabase.productDao().getAllProduct() }
    ).liveData

    override suspend fun loadProducts(page: Int): Flow<Response<Product2>> = flow {
        emit(Response.loading<Product2>())
        val list = kotlin.runCatching {
            apiService2.getLapTop2(page = page).body()?.results?.toMutableList()
        }.getOrNull()
        list?.takeIf { it.isNotEmpty() }?.run {
            emit(Response.success<Product2>(this))
        } ?: kotlin.run {
            emit(Response.notFound<Product2>())
        }
    }

    override fun loadProductsLive(page: Int): LiveData<Response<Product2>> {
        return MutableLiveData<Response<Product2>>().also { live ->
            live.value = Response.loading()
            CoroutineScope(Dispatchers.IO).launch {
                val list = kotlin.runCatching {
                    apiService2.getLapTop2(page = page).body()?.results?.toMutableList()
                }.getOrNull()
                list?.takeIf { it.isNotEmpty() }?.run {
                    live.postValue(Response.success(this))
                } ?: kotlin.run {
                    live.postValue(Response.notFound<Product2>())
                }
            }
        }
    }

    override suspend fun loadProductsSuspend(page: Int): Response<Product2> {
        return kotlin.runCatching {
            apiService2.getLapTop2(page).body()?.results?.toMutableList()?.run {
                Response.success(this)
            } ?: Response.notFound<Product2>()
        }.getOrElse {
            Response.notFound<Product2>()
        }
    }
}