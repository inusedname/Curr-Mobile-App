package dev.vstd.shoppingcart.domain

data class UserInfo(
    val username: String,
    val email: String,
    val address: String?,
    val balance: Int,
)