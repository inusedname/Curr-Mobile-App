package dev.vstd.shoppingcart.data.remote.user

data class CreditCard(
    val cardNumber: String,
    val personName: String,
    val expireDate: String,
)