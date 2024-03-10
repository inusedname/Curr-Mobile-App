package dev.vstd.shoppingcart.data.remote

data class Order(
    val orderAddress: OrderAddress,
    val products: List<OrderProduct>,
    val paymentType: PaymentType,
) {
    data class OrderAddress(
        val city: String,
        val district: String,
        val address: String,
        val telephone: String,
    )

    data class OrderProduct(
        val id: Int,
    )
}
