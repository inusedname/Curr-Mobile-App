package dev.vstd.shoppingcart.data.remote.comparing

import dev.keego.shoppingcart.BuildConfig
import dev.vstd.shoppingcart.Constants
import dev.vstd.shoppingcart.data.remote.comparing.pojo.ProductSearchResult
import dev.vstd.shoppingcart.data.remote.comparing.pojo.SellerSearchResult
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ComparingPriceService {

    @GET("/search")
    suspend fun searchProduct(
        @Query("product", encoded = true) productName: String,
        @Query("api_key") apiKey: String = BuildConfig.APIKEY_PRICECOMPARE,
    ): Response<ProductSearchResult>

    @GET("/detail")
    suspend fun getProductSeller(
        @Query("id", encoded = true) productId: String,
        @Query("api_key") apiKey: String = BuildConfig.APIKEY_PRICECOMPARE,
    ): Response<SellerSearchResult>

    companion object {
        // TODO: Enable cache
        fun build(): ComparingPriceService {
            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.comparingServiceUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ComparingPriceService::class.java)
        }
    }
}