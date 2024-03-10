package dev.vstd.shoppingcart.data.remote

import com.google.gson.annotations.SerializedName

enum class PaymentType {
    @SerializedName("cod")
    CASH_ON_DELIVERY,

    @SerializedName("momo")
    MOMO,

    @SerializedName("credit_card")
    CREDIT_CARD,
}