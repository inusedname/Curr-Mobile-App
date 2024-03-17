package dev.vstd.shoppingcart.ui.payment.checkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vstd.shoppingcart.data.remote.CheckoutRepository
import dev.vstd.shoppingcart.data.remote.MakeOrderResp
import dev.vstd.shoppingcart.data.remote.Order
import dev.vstd.shoppingcart.data.remote.PaymentType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CheckoutVimel @Inject constructor(private val repo: CheckoutRepository) : ViewModel() {
    val orderAddress = Order.OrderAddress(
        city = "",
        district = "",
        address = "",
        telephone = ""
    )
    val products = mutableListOf<Order.OrderProduct>()
    val paymentType = PaymentType.CASH_ON_DELIVERY
    fun makeOrder(onComplete: (MakeOrderResp?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val resp = repo.makeOrder(
                Order(
                    orderAddress,
                    products,
                    paymentType
                )
            )
            when (resp.isSuccessful) {
                true -> onComplete(resp.body())
                false -> Timber.d("Error: ${resp.errorBody()}")
            }
        }
    }
}