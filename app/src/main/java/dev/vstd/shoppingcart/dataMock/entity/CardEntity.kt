package dev.vstd.shoppingcart.dataMock.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CardEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: Long,
    val cardNumber: String,
    val cardHolder: CardHolder,
    val expirationDate: String,
    val cvv: String
) {
    enum class CardHolder {
        VISA, MASTER_CARD, AMERICAN_EXPRESS
    }
}