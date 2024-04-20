package dev.vstd.shoppingcart.pricecompare.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vstd.shoppingcart.pricecompare.data.ComparingPriceRepository
import dev.vstd.shoppingcart.pricecompare.data.ComparingPriceService
import dev.vstd.shoppingcart.pricecompare.data.model.ComparingProduct
import dev.vstd.shoppingcart.pricecompare.data.model.SellerInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ComparingVimel @Inject constructor(okHttpClient: OkHttpClient): ViewModel() {
    private val compareRepo = ComparingPriceRepository(ComparingPriceService.build(okHttpClient))
    val products = MutableStateFlow(listOf<ComparingProduct>())
    private var searchingProductId: String? = null
    var searchingProductImageUrl: String? = null
        private set
    val sellers = MutableStateFlow(listOf<SellerInfo>())

    fun searchProduct(productName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            compareRepo.searchProduct(productName).let {
                Timber.d("Search product completed!")
                if (it.isSuccessful) {
                    products.value = it.body()!!
                }
            }
        }
    }

    fun getSellers(product: ComparingProduct) {
        searchingProductId = product.id
        searchingProductImageUrl = product.image
        viewModelScope.launch(Dispatchers.IO) {
            compareRepo.getProductSeller(product.id).let {
                Timber.d("Search sellers completed!")
                if (it.isSuccessful) {
                    sellers.value = it.body()!!
                }
            }
        }
    }
}