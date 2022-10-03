package com.example.data.repository.product

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.api.event.APIService2
import com.example.data.model.Product2
import com.example.data.room.AppDatabase
import retrofit2.HttpException
import java.io.IOException

internal class ProductDataSource2(
    private val apiService2: APIService2,
    private val appDatabase: AppDatabase
) :
    PagingSource<Int, Product2>() {
    private val DEFAULT_PAGE_INDEX = 1
    override fun getRefreshKey(state: PagingState<Int, Product2>): Int? {
        return DEFAULT_PAGE_INDEX
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product2> {
        val page = params.key ?: DEFAULT_PAGE_INDEX
        return try {
            val response = apiService2.getLapTop2(page)
            response.body()?.results?.forEach {
                appDatabase.productDao().insertProduct(it)
            }
            LoadResult.Page(
                data = response.body()?.results!!,
                prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                nextKey = if (response.body()?.results!!.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}