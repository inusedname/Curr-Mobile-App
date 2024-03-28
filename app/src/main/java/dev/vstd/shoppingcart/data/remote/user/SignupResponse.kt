package dev.vstd.shoppingcart.data.remote.user

data class SignupResponse(
    val message: String,
    val token: String,
    val username: String
)