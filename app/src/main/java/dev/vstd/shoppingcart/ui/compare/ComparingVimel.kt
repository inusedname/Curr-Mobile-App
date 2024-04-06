package dev.vstd.shoppingcart.ui.compare

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.vstd.shoppingcart.data.remote.comparing.ComparingPriceService
import dev.vstd.shoppingcart.data.remote.comparing.ComparingPriceServiceImpl
import dev.vstd.shoppingcart.data.remote.comparing.model.ComparingProduct
import dev.vstd.shoppingcart.data.remote.comparing.model.SellerInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ComparingVimel: ViewModel() {
    private val compareService: ComparingPriceService = ComparingPriceServiceImpl.build()
    val products = MutableStateFlow(listOf<ComparingProduct>())
    var searchingProductId: String? = null
    val sellers = MutableStateFlow(listOf<SellerInfo>())

    fun searchProduct(productName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            compareService.searchProduct(productName).let {
                if (it.isSuccessful) {
                    products.value = it.body()!!
                }
            }
        }
    }

    fun getSellers(productId: String) {
        searchingProductId = productId
        viewModelScope.launch(Dispatchers.IO) {
            compareService.getProductSeller(productId).let {
                if (it.isSuccessful) {
                    sellers.value = it.body()!!
                }
            }
        }
    }
}