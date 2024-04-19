package dev.vstd.shoppingcart.dataMock.dao

import androidx.room.Dao
import androidx.room.Query
import dev.vstd.shoppingcart.dataMock.entity.ProductEntity

@Dao
interface ProductDao {

    @Query("SELECT * FROM ProductEntity")
    fun getAll(): List<ProductEntity>
}