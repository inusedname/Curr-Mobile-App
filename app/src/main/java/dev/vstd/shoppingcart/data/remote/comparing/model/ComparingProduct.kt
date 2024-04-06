package dev.vstd.shoppingcart.data.remote.comparing.model

data class ComparingProduct(
    val id: String,
    val title: String,
    val image: String,
    val lowestPrice: Int,
)