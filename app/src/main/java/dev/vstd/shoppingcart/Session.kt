package dev.vstd.shoppingcart

import dev.vstd.shoppingcart.dataMock.entity.UserEntity
import kotlinx.coroutines.flow.MutableStateFlow

object Session {
    var userEntity = MutableStateFlow<UserEntity?>(null)
}