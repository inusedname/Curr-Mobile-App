package dev.vstd.shoppingcart.auth.domain

data class UserInfo(
    val username: String,
    val email: String,
    val address: String?,
    val balance: Int,
)