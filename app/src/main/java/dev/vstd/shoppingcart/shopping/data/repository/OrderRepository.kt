package dev.vstd.shoppingcart.shopping.data.repository

import dev.vstd.shoppingcart.auth.Session
import dev.vstd.shoppingcart.shopping.data.dao.OrderDao
import dev.vstd.shoppingcart.shopping.data.entity.OrderEntity

class OrderRepository(private val orderRepository: OrderDao) {
    fun getAllOrders(): List<OrderEntity> {
        return orderRepository.getAllOrders(Session.userEntity.value!!.id)
    }
}