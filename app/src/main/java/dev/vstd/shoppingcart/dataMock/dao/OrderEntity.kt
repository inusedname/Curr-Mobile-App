package dev.vstd.shoppingcart.dataMock.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface OrderEntity {

    @Query("SELECT * FROM OrderEntity WHERE userId = :userId")
    fun getAllOrders(userId: Long): List<OrderEntity>

    @Insert
    fun insert(orderEntity: OrderEntity)

    @Update
    fun update(orderEntity: OrderEntity)
}