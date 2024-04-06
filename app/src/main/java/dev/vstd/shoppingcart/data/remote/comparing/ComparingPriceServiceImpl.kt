package dev.vstd.shoppingcart.data.remote.comparing

import dev.keego.shoppingcart.BuildConfig
import dev.vstd.shoppingcart.Constants
import dev.vstd.shoppingcart.data.remote.comparing.model.ComparingProduct
import dev.vstd.shoppingcart.data.remote.comparing.model.SellerInfo
import dev.vstd.shoppingcart.data.remote.comparing.pojo.ProductSearchResult
import dev.vstd.shoppingcart.data.remote.comparing.pojo.SellerSearchResult
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ComparingPriceServiceImpl: ComparingPriceService {

    override suspend fun searchProduct(productName: String): Response<List<ComparingProduct>> {
        val resp = _search_product(productName)
        return if (resp.isSuccessful) {
            val products = resp.body()!!.data.map(ProductSearchResult.PojoProduct::toComparingProduct)
            Response.success(products, resp.raw())
        } else {
            Response.error(resp.code(), resp.errorBody()!!)
        }
    }

    override suspend fun getProductSeller(productId: String): Response<List<SellerInfo>> {
        val resp = _get_product_seller(productId)
        return if (resp.isSuccessful) {
            val sellers = resp.body()!!.data.getStores().map(SellerSearchResult.Data.Store::toSellerInfo)
            Response.success(sellers, resp.raw())
        } else {
            Response.error(resp.code(), resp.errorBody()!!)
        }
    }

    @GET("/product")
    suspend fun _search_product(
        @Query("product", encoded = true) productName: String,
        @Query("api_key") apiKey: String = BuildConfig.APIKEY_PRICECOMPARE,
    ): Response<ProductSearchResult>

    @GET("/detail")
    suspend fun _get_product_seller(
        @Query("id", encoded = true) productId: String,
        @Query("api_key") apiKey: String = BuildConfig.APIKEY_PRICECOMPARE,
    ): Response<SellerSearchResult>

    companion object {
        // TODO: Enable cache
        fun build(): ComparingPriceService {
            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.backendUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ComparingPriceServiceImpl::class.java)
        }
    }
}