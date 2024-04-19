package dev.vstd.shoppingcart.shopping.data.repository

import dev.vstd.shoppingcart.auth.Session
import dev.vstd.shoppingcart.shopping.data.dao.OrderDao
import dev.vstd.shoppingcart.shopping.data.entity.OrderEntity

class OrderRepository(private val orderDao: OrderDao) {
    suspend fun getAllOrders(): List<OrderEntity> {
        return orderDao.getAllOrders(Session.userEntity.value!!.id)
    }
    suspend fun createOrder(orderEntity: OrderEntity) {
        orderDao.insert(orderEntity)
    }
}