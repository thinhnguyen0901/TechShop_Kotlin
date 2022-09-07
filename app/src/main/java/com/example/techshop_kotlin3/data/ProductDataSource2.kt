package com.example.techshop_kotlin3.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.techshop_kotlin2.event.APIService2
import com.example.techshop_kotlin3.models.Product2
import retrofit2.HttpException
import java.io.IOException

class ProductDataSource2 (private val apiService2: APIService2):
    PagingSource<Int, Product2>() {
    private val DEFAULT_PAGE_INDEX = 1
    override fun getRefreshKey(state: PagingState<Int, Product2>): Int? {
        return DEFAULT_PAGE_INDEX
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product2> {
        val page = params.key ?: DEFAULT_PAGE_INDEX
        return try {
            val response = apiService2.getLapTop2(page)
            LoadResult.Page(
                data = response.body()?.data?.data!!,
                prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                nextKey = if (response.body()!!.data!!.data!!.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

}