package dev.vstd.shoppingcart.data.remote.user

data class SignupRequest(
    val email: String,
    val username: String,
    val password: String,
)
