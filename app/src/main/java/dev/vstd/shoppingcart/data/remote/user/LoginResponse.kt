package dev.vstd.shoppingcart.data.remote.user

data class LoginResponse(
    val message: String,
    val token: String,
    val username: String
)
