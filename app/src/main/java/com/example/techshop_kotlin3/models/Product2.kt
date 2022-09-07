package com.example.techshop_kotlin3.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Product2(
    var id: Int,
    var name: String? = null,
    var price: Int? = null,
    var description: String? = null,
    var picture: String? = null,
    var is_disabled: Int? = 0,
    var brand_id: Int? = 0,
    var product_type_id: Int? = 0
)