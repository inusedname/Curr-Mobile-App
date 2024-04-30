package dev.vstd.shoppingcart.shopping.data.service

import dev.vstd.shoppingcart.shopping.data.entity.OrderEntity

data class CreateOrderBodyDto(
    val products: List<ProductOfOrderDto>,
    val address: String,
    val purchaseMethod: OrderEntity.PurchaseMethod,
    val purchaseMethodId: Long?
) {
    class ProductOfOrderDto(
        val productId: Long,
        val quantity: Int,
    )
}