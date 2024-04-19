package dev.vstd.shoppingcart.shopping.domain

import androidx.annotation.DrawableRes

data class Product(
    @DrawableRes val image: Int,
    val title: String,
    val category: String,
    val price: Int,
    val quantity: Int,
    val id: Int
) {
    companion object {
        fun getFakeProduct() = Product(
            0,
            "Lenovo LP40 Pro Case",
            "Phân loại: Lenovo LP40 Pro",
            28000,
            1,
            0
        )
    }
}