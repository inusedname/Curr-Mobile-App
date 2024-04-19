package dev.vstd.shoppingcart.dataMock.repository

import dev.vstd.shoppingcart.dataMock.entity.OrderEntity

class OrderRepository(private val orderRepository: OrderRepository) {
    fun getAllOrders(): List<OrderEntity> {
        return orderRepository.getAllOrders()
    }
}