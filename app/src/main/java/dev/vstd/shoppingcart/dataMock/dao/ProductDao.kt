package dev.vstd.shoppingcart.dataMock.dao

import androidx.room.Dao
import androidx.room.Query
import dev.vstd.shoppingcart.dataMock.entity.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM product")
    fun getAll(): List<Product>
}