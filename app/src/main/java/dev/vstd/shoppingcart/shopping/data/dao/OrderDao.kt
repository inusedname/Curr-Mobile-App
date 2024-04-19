package dev.vstd.shoppingcart.shopping.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import dev.vstd.shoppingcart.shopping.data.entity.OrderEntity

@Dao
interface OrderDao {

    @Query("SELECT * FROM OrderEntity WHERE userId = :userId")
    suspend fun getAllOrders(userId: Long): List<OrderEntity>

    @Insert
    suspend fun insert(orderEntity: OrderEntity)

    @Update
    suspend fun update(orderEntity: OrderEntity)
}