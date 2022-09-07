package com.example.techshop_kotlin3.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.techshop_kotlin3.data.ProductDataSourceFactory
import com.example.techshop_kotlin3.models.Product2
import com.example.techshop_kotlin3.repository.ProductRepositoryImpl
import com.example.techshop_kotlin3.response.ProductResponse2
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductViewModel @Inject constructor(private val productRepositoryImpl : ProductRepositoryImpl) : ViewModel() {
    //private val productRepository = ProductRepository()
    private val _productLiveData = MutableLiveData<ProductResponse2>()
    val productLiveData: LiveData<ProductResponse2> = _productLiveData

    var productLiveData2: LiveData<PagingData<Product2>> = productRepositoryImpl.getProduct().cachedIn(viewModelScope)

    private val pagedListConfig: PagedList.Config = PagedList.Config.Builder()
        .setPageSize(10).setPrefetchDistance(20).build()
    val dataSourceFactory = ProductDataSourceFactory(viewModelScope, productRepositoryImpl)
    var list: LiveData<PagedList<Product2>> = LivePagedListBuilder(dataSourceFactory, pagedListConfig).build()
    //val test = MutableLiveData<String>()

    /*fun refreshProduct(){
        viewModelScope.launch {
            val response = productRepository.getProductLapTop()
            _productLiveData.postValue(response)
        }*/

    init {
        viewModelScope.launch {
            productRepositoryImpl.getProductLapTop()?.run {
                _productLiveData.postValue(this)
            }
        }
    }


}