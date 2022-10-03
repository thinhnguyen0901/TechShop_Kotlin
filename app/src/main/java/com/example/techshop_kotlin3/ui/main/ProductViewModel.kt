package com.example.techshop_kotlin3.ui.main

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.data.model.Product2
import com.example.data.model.ProductResponse2
import com.example.data.model.Response
import com.example.data.repository.product.ProductRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flatMapMerge
import javax.inject.Inject

class ProductViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {
    
    //private val productRepository = ProductRepository()
    private val _productLiveData = MutableLiveData<ProductResponse2>()
    val productLiveData: LiveData<ProductResponse2> = _productLiveData

    var productLiveData2: LiveData<PagingData<Product2>> =
        productRepository.getProduct().cachedIn(viewModelScope)

    var productDBLiveData: LiveData<PagingData<Product2>> =
        productRepository.getProductDB().cachedIn(viewModelScope)


    // dữ liệu đầu vào
    var page = MutableLiveData<Int>()

    var currentResponse : Response<Product2>?=null

    // dữ liệu trạng thái dang flow
    @OptIn(FlowPreview::class)
    var productResponse = page.asFlow().flatMapMerge {
        productRepository.loadProducts(it ?: 1)
    }

    // dữ liệu trạng thái dangj liveData
    var productLiveResponse = Transformations.switchMap(page) {
        productRepository.loadProductsLive(it ?: 1)
    }
}