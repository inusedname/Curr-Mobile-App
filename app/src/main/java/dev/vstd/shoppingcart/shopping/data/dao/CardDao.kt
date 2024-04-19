package dev.vstd.shoppingcart.shopping.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.vstd.shoppingcart.shopping.data.entity.CardEntity

@Dao
interface CardDao {
    @Query("SELECT * FROM CardEntity WHERE userId = :userId")
    suspend fun getCard(userId: Long): CardEntity?

    @Insert
    suspend fun insert(cardEntity: CardEntity)
}