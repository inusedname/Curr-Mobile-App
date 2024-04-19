package dev.vstd.shoppingcart

import dev.vstd.shoppingcart.domain.UserCredential
import kotlinx.coroutines.flow.MutableStateFlow

object Session {
    var userEntity = MutableStateFlow<UserCredential?>(UserCredential(
        id = 1,
        username = "John Doe",
    ))
}