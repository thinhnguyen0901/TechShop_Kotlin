package com.example.data.repository

import com.example.data.repository.product.ProductRepositoryImpl
import com.example.data.repository.product.ProductRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun providesRepository(productRepositoryImpl: ProductRepositoryImpl): ProductRepository

}