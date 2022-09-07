package com.example.techshop_kotlin2.event


import com.example.techshop_kotlin3.response.PageProductResponse
import com.example.techshop_kotlin3.response.ProductResponse2
import retrofit2.Response
import retrofit2.http.*

interface APIService2 {
    //Product
    //convert
    /*@get:GET("product/app")
    val all: Call<ProductResponse2?>?

    @get:GET("product/laptop")
    val lapTop: Call<ProductResponse2?>?

    @get:GET("product/smartphone")
    val smartPhone: Call<ProductResponse2?>?

    @get:GET("product/part")
    val part: Call<ProductResponse2?>?*/

    @GET("product/laptop")
    suspend fun getLapTop(): Response<ProductResponse2>

    @GET("product/laptop")
    suspend fun getLapTop2(@Query("page") page: Int): Response<PageProductResponse>
}