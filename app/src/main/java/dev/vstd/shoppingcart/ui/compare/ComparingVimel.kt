package dev.vstd.shoppingcart.ui.compare

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.vstd.shoppingcart.data.remote.comparing.ComparingPriceRepository
import dev.vstd.shoppingcart.data.remote.comparing.ComparingPriceService
import dev.vstd.shoppingcart.data.remote.comparing.model.ComparingProduct
import dev.vstd.shoppingcart.data.remote.comparing.model.SellerInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ComparingVimel: ViewModel() {
    private val compareRepo = ComparingPriceRepository(ComparingPriceService.build())
    val products = MutableStateFlow(listOf<ComparingProduct>())
    private var searchingProductId: String? = null
    val sellers = MutableStateFlow(listOf<SellerInfo>())

    fun searchProduct(productName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            compareRepo.searchProduct(productName).let {
                if (it.isSuccessful) {
                    products.value = it.body()!!
                }
            }
        }
    }

    fun getSellers(productId: String) {
        searchingProductId = productId
        viewModelScope.launch(Dispatchers.IO) {
            compareRepo.getProductSeller(productId).let {
                if (it.isSuccessful) {
                    sellers.value = it.body()!!
                }
            }
        }
    }
}