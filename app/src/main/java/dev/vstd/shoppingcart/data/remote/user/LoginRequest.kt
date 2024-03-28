package dev.vstd.shoppingcart.data.remote.user

data class LoginRequest(
    val email: String,
    val password: String,
)
