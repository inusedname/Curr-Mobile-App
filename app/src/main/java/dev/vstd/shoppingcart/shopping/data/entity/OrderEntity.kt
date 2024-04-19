package dev.vstd.shoppingcart.shopping.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OrderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: Long,
    val status: Status = Status.PENDING,
    val shippingAddress: String,
    val purchaseMethodType: PurchaseMethod,
    val purchaseMethodId: Int? = null,
) {
    enum class PurchaseMethod {
        COD,
        OTHER,
    }
    enum class Status {
        PENDING,
        SHIPPED,
        DELIVERED,
        CANCELLED,
    }
}