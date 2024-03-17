package dev.vstd.shoppingcart.data.remote

data class MakeOrderResp(
    val code: Int,
    val paymentType: PaymentType?,
    val paymentInfo: String?
)