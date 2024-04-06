package dev.vstd.shoppingcart.data.remote.comparing

import dev.vstd.shoppingcart.data.remote.comparing.model.ComparingProduct
import dev.vstd.shoppingcart.data.remote.comparing.model.SellerInfo
import retrofit2.Response

interface ComparingPriceService {
    suspend fun searchProduct(productName: String): Response<List<ComparingProduct>>

    suspend fun getProductSeller(productId: String): Response<List<SellerInfo>>
}