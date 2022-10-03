package com.example.data.api

import com.example.data.api.event.APIService2
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class ApiModule {
    val baseUrl = "https://rickandmortyapi.com/api/"//192.168.8.11 192.168.1.164 http://192.168.8.11:8000/api/

    @Singleton
    @Provides
    fun getInstance(): Retrofit {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder().baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
    }

    @Singleton
    @Provides
    fun getRetrofitService(retrofit: Retrofit): APIService2 {
        return retrofit.create(APIService2::class.java)
    }
}