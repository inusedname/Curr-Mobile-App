package dev.vstd.shoppingcart.shopping.data.repository

import dev.vstd.shoppingcart.shopping.data.entity.OrderEntity
import dev.vstd.shoppingcart.shopping.data.service.CreateOrderBodyDto
import dev.vstd.shoppingcart.shopping.data.service.OrderService
import retrofit2.Response

class OrderRepository(private val orderService: OrderService) {
    suspend fun getAllOrders(): Response<List<OrderEntity>> {
        return orderService.getOrders()
    }
    suspend fun createOrder(body: CreateOrderBodyDto): Response<OrderEntity> {
        return orderService.makeOrder(body)
    }
}