package com.example.data.room.dao

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.Product2

@Dao
interface ProductDao {

    @Query("Select * from products")
    fun getAllProduct(): PagingSource<Int, Product2>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product2: Product2): Long

    @Query("Select * from products")
    fun getAllProductLive(): LiveData<MutableList<Product2>>

    @Query("Select * from products")
    suspend fun getAllProductSuspend(): MutableList<Product2>

}