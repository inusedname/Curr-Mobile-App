package dev.vstd.shoppingcart.dataMock.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.vstd.shoppingcart.dataMock.entity.CardEntity

@Dao
interface CardDao {
    @Query("SELECT * FROM CardEntity WHERE userId = :userId")
    suspend fun getCard(userId: Long): CardEntity?

    @Insert
    suspend fun insert(cardEntity: CardEntity)
}