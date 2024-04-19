package dev.vstd.shoppingcart.shopping.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import dev.vstd.shoppingcart.shopping.data.entity.OrderEntity

@Dao
interface OrderDao {

    @Query("SELECT * FROM OrderEntity WHERE userId = :userId")
    fun getAllOrders(userId: Long): List<OrderEntity>

    @Insert
    fun insert(orderEntity: OrderEntity)

    @Update
    fun update(orderEntity: OrderEntity)
}