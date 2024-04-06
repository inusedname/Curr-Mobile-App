package dev.vstd.shoppingcart.domain.pojo

import com.google.gson.annotations.SerializedName

data class ProductSearchResult(
    val data: List<PojoProduct>,
) {
    data class PojoProduct(
        @SerializedName("product_id")
        val id: String,
        @SerializedName("product_title")
        val title: String,
        @SerializedName("product_image")
        val image: String,
        @SerializedName("product_lowest_price")
        val lowestPrice: Int,
    )
}