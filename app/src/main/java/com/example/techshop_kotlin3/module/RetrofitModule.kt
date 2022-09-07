package com.example.techshop_kotlin3.module

import com.example.techshop_kotlin2.event.APIService2
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {
    val baseUrl = "http://192.168.8.11:8000/api/"//192.168.8.11 192.168.1.164

    @Singleton
    @Provides
    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
    }

    @Singleton
    @Provides
    fun getRetrofitService(retrofit: Retrofit): APIService2{
        return retrofit.create(APIService2::class.java)
    }
}