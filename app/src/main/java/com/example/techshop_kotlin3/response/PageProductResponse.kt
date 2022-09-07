package com.example.techshop_kotlin3.response

import com.example.techshop_kotlin3.models.ProductList

data class PageProductResponse(var success: Boolean = false,
                               var message: String?=null,
                               var data: ProductList? = null,
                               var current_page: Int? = 0,
                               var last_page: Int? = 0,
                               var per_page: Int? = 0)