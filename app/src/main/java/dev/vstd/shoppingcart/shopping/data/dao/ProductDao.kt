package dev.vstd.shoppingcart.shopping.data.dao

import androidx.room.Dao
import androidx.room.Query
import dev.vstd.shoppingcart.shopping.data.entity.ProductEntity

@Dao
interface ProductDao {

    @Query("SELECT * FROM ProductEntity")
    fun getAll(): List<ProductEntity>
}