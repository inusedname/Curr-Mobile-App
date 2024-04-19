package dev.vstd.shoppingcart.shopping.ui.payment.checkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vstd.shoppingcart.auth.Session
import dev.vstd.shoppingcart.shopping.data.entity.CardEntity
import dev.vstd.shoppingcart.shopping.data.repository.OrderRepository
import dev.vstd.shoppingcart.shopping.data.repository.UserRepository
import dev.vstd.shoppingcart.shopping.domain.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckoutVimel @Inject constructor(
    private val userRepository: UserRepository,
    private val orderRepository: OrderRepository
): ViewModel() {
    val address = MutableStateFlow<String?>(null)
    val card = MutableStateFlow<CardEntity?>(null)
    val products = MutableStateFlow(listOf<Product>())

    fun fetch() {
        viewModelScope.launch {
            address.value = userRepository.getAddress(Session.userEntity.value!!.id)
            card.value = userRepository.getCards(Session.userEntity.value!!.id).firstOrNull()
            products.value = listOf(Product.getFakeProduct())
        }
    }

    fun setAddress(address: String) {
        viewModelScope.launch {
            userRepository.updateAddress(Session.userEntity.value!!.id, address)
            this@CheckoutVimel.address.value = address
        }
    }
}