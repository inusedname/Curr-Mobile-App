package dev.vstd.shoppingcart.shopping.ui.payment.checkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vstd.shoppingcart.auth.Session
import dev.vstd.shoppingcart.auth.data.UserRepository
import dev.vstd.shoppingcart.shopping.data.entity.OrderEntity
import dev.vstd.shoppingcart.shopping.data.repository.OrderRepository
import dev.vstd.shoppingcart.shopping.domain.PaymentMethod
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
    val paymentMethod = MutableStateFlow(PaymentMethod.getDefaultOptions()[1])
    val products = MutableStateFlow(listOf<Product>())
    val total = MutableStateFlow(0)

    fun fetch() {
        viewModelScope.launch {
            address.value = userRepository.getAddress(Session.userEntity.value!!.id)
            products.value = listOf(Product.getFakeProduct())
            total.value = products.value.sumOf { it.price } + 15000
        }
    }

    fun setPaymentMethod(paymentMethod: PaymentMethod) {
        this.paymentMethod.value = paymentMethod
    }

    fun setAddress(address: String) {
        viewModelScope.launch {
            userRepository.updateAddress(Session.userEntity.value!!.id, address)
            this@CheckoutVimel.address.value = address
        }
    }

    fun createOrder(goToPaymentFlow: (PaymentMethod.Type) -> Unit) {
        viewModelScope.launch {
            orderRepository.createOrder(OrderEntity(
                userId = Session.userEntity.value!!.id,
                shippingAddress = address.value!!,
                purchaseMethodType = OrderEntity.PurchaseMethod.COD,
                purchaseMethodId = paymentMethod.value.id
            ))
            goToPaymentFlow(paymentMethod.value.type)
        }
    }
}