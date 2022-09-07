package com.example.techshop_kotlin3.db

import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "products")
data class ProductTable(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "name")
    var name: String? = null,
    @ColumnInfo(name = "price")
    var price: Int? = null,
    @ColumnInfo(name = "description")
    var description: String? = null,
    @ColumnInfo(name = "picture")
    var picture: String? = null,
    @ColumnInfo(name = "is_disabled")
    var is_disabled: Int? = 0,
    @ColumnInfo(name = "brand_id")
    var brand_id: Int? = 0,
    @ColumnInfo(name = "product_type_id")
    var product_type_id: Int? = 0
    )