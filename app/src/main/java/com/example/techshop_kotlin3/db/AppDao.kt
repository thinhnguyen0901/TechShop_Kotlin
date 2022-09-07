package com.example.techshop_kotlin3.db

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.techshop_kotlin3.models.Product2
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
    @Query("Select * from products")
    fun getAllProduct(): Flow<List<Product2>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(product2: Product2)
}