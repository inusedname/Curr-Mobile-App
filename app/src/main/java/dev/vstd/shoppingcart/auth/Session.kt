package dev.vstd.shoppingcart.auth

import dev.vstd.shoppingcart.auth.domain.UserCredential
import kotlinx.coroutines.flow.MutableStateFlow

object Session {
    var userEntity = MutableStateFlow<UserCredential?>(
        UserCredential(
        id = 1,
        username = "John Doe",
    )
    )
}