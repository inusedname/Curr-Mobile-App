package dev.vstd.shoppingcart.data.remote

class CheckoutRepository(private val checkoutService: CheckoutService) {
    suspend fun makeOrder(order: Order) = checkoutService.makeOrder(order)
}