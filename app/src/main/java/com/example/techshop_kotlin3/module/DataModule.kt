package com.example.techshop_kotlin3.module

import com.example.techshop_kotlin2.event.APIService2
import com.example.techshop_kotlin3.repository.ProductRepositoryImpl
import com.example.techshop_kotlin3.repository.ProductRespository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun providesRepository(productRepositoryImpl: ProductRepositoryImpl): ProductRespository

    companion object{
        @Singleton
        @Provides
        fun productRepository(apiService2: APIService2): ProductRepositoryImpl =
            ProductRepositoryImpl(apiService2)
    }

}