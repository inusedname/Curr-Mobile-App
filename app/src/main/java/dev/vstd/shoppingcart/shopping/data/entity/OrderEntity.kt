package dev.vstd.shoppingcart.shopping.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.vstd.shoppingcart.shopping.domain.Order
import dev.vstd.shoppingcart.shopping.domain.Product
import dev.vstd.shoppingcart.shopping.domain.Status

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
    fun toOrder(): Order {
        return Order(
            id = id,
            status = status,
            sellerName = "TỔNG KHO LINH ĐÀM",
            products = List(3) { Product.getFakeProduct() },
        )
    }
}