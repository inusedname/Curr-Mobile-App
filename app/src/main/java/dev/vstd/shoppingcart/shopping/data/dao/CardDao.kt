package dev.vstd.shoppingcart.shopping.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import dev.vstd.shoppingcart.shopping.data.entity.CardEntity

@Dao
interface CardDao {
    @Query("SELECT * FROM CardEntity WHERE userId = :userId")
    suspend fun getCard(userId: Long): CardEntity?

    @Insert
    suspend fun insert(cardEntity: CardEntity)

    @Update
    suspend fun update(cardEntity: CardEntity)

    suspend fun cashOut(userId: Long, amount: Int) {
        val card = getCard(userId) ?: return
        update(card.copy(balance = card.balance - amount))
    }

    suspend fun topUp(userId: Long, amount: Int) {
        val card = getCard(userId) ?: return
        update(card.copy(balance = card.balance + amount))
    }
}