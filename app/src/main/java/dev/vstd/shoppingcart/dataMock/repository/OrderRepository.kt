package dev.vstd.shoppingcart.dataMock.repository

import dev.vstd.shoppingcart.Session
import dev.vstd.shoppingcart.dataMock.dao.OrderDao
import dev.vstd.shoppingcart.dataMock.entity.OrderEntity

class OrderRepository(private val orderRepository: OrderDao) {
    fun getAllOrders(): List<OrderEntity> {
        return orderRepository.getAllOrders(Session.userEntity.value!!.id)
    }
}