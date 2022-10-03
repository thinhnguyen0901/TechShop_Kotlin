package com.example.data.model

data class PageProductResponse(var success: Boolean = false,
                               var message: String?=null,
                               var data: ProductList? = null,
                               var current_page: Int? = 0,
                               var last_page: Int? = 0,
                               var per_page: Int? = 0)