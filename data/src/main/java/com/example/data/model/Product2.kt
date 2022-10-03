package com.example.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product2(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "name")
    var name: String? = null,
//    var price: Int? = null,
//    var description: String? = null,
//    var picture: String? = null,
//    var is_disabled: Int? = 0,
//    var brand_id: Int? = 0,
//    var product_type_id: Int? = 0
)