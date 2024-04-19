package dev.vstd.shoppingcart.dataMock.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OrderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: Long,
    val status: Status,
    val shippingAddress: String,
    val purchaseMethod: PurchaseMethod
) {
    enum class PurchaseMethod {
        COD,
        CARD,
    }
    enum class Status {
        PENDING,
        SHIPPED,
        DELIVERED,
        CANCELLED,
    }
}