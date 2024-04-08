package dev.vstd.shoppingcart.data.remote.comparing

import dev.vstd.shoppingcart.data.remote.comparing.model.ComparingProduct
import dev.vstd.shoppingcart.data.remote.comparing.model.SellerInfo
import dev.vstd.shoppingcart.data.remote.comparing.pojo.ProductSearchResult
import dev.vstd.shoppingcart.data.remote.comparing.pojo.SellerSearchResult
import retrofit2.Response

class ComparingPriceRepository(private val comparingService: ComparingPriceService) {
    suspend fun searchProduct(productName: String): Response<List<ComparingProduct>> {
        val resp = comparingService.searchProduct(productName)
        return if (resp.isSuccessful) {
            val products = resp.body()!!.data.map(ProductSearchResult.PojoProduct::toComparingProduct)
            Response.success(products, resp.raw())
        } else {
            Response.error(resp.code(), resp.errorBody()!!)
        }
    }

    suspend fun getProductSeller(productId: String): Response<List<SellerInfo>> {
        val resp = comparingService.getProductSeller(productId)
        return if (resp.isSuccessful) {
            val sellers = resp.body()!!.data.getStores().map(SellerSearchResult.Data.Store::toSellerInfo)
            Response.success(sellers, resp.raw())
        } else {
            Response.error(resp.code(), resp.errorBody()!!)
        }
    }
}