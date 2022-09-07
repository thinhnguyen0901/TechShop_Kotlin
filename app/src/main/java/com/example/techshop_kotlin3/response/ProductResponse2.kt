package com.example.techshop_kotlin3.response

import com.example.techshop_kotlin3.models.ProductList

//convert
/*class ProductResponse2 {
    @SerializedName("success")
    @Expose
    var success = false

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("data")
    @Expose
    var data: Data? = null

    @SerializedName("current_page")
    @Expose
    var current_page = 0

    @SerializedName("last_page")
    @Expose
    var last_page = 0

    @SerializedName("per_page")
    @Expose
    var per_page = 0

    @SerializedName("total")
    @Expose
    var total = 0

    inner class Data {
        @SerializedName("data")
        @Expose
        var listProduct: List<Product2> = ArrayList()
    }
}*/
data class ProductResponse2(var success: Boolean = false,
                            var message: String?=null,
                            var data: ProductList? = null,
                            var current_page: Int? = 0,
                            var last_page: Int? = 0,
                            var per_page: Int? = 0
)