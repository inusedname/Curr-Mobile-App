package dev.vstd.shoppingcart.shopping.ui.payment.checkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vstd.shoppingcart.auth.Session
import dev.vstd.shoppingcart.auth.data.UserRepository
import dev.vstd.shoppingcart.shopping.data.repository.OrderRepository
import dev.vstd.shoppingcart.shopping.data.service.CreateOrderBodyDto
import dev.vstd.shoppingcart.shopping.domain.PaymentMethod
import dev.vstd.shoppingcart.shopping.domain.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckoutVimel @Inject constructor(
    private val userRepository: UserRepository,
    private val orderRepository: OrderRepository
) : ViewModel() {
    val address = MutableStateFlow<String?>(null)
    val paymentMethod = MutableStateFlow(PaymentMethod.getDefaultOptions()[1])
    val products = MutableStateFlow(listOf<Product>())
    val shipFee = MutableStateFlow(15000)
    val ableToPurchase =
        combine(address, paymentMethod, products) { address, paymentMethod, products ->
            address != null && paymentMethod.type != PaymentMethod.Type.MOMO && products.isNotEmpty()
        }

    fun getTotal(): Int {
        return products.value.fold(0) { sum, it -> sum + it.price } + shipFee.value
    }

    fun fetch() {
        viewModelScope.launch {
            address.value = userRepository.getAddress(Session.userEntity.value!!.id)
            products.value = List(3) { Product.getFakeProduct() }
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
            val resp = orderRepository.createOrder(
                CreateOrderBodyDto(
                    address = address.value!!,
                    purchaseMethod = TODO(),
                    purchaseMethodId = TODO(),
                    products = products.value.map {
                        CreateOrderBodyDto.ProductOfOrderDto(
                            productId = it.id,
                            quantity = 1
                        )
                    }
                )
            )
            if (resp.isSuccessful) {
                goToPaymentFlow(paymentMethod.value.type)
            } else {
                // TODO
            }
        }
    }
}