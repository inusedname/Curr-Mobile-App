package dev.vstd.shoppingcart.shopping.data.service

import dev.vstd.shoppingcart.common.Constants
import dev.vstd.shoppingcart.shopping.data.entity.CardEntity
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CardService {
    @GET("card")
    suspend fun getCard(@Query("userId") userId: Long): Response<CardEntity>

    @POST("card")
    suspend fun registerCard(@Query("userId") userId: Long): Response<CardEntity>

    @POST("validate-cvv")
    suspend fun validateCVV(@Query("userId") userId: Long, @Query("cvv") cvv: String): Response<String>

    companion object {
        fun create(okHttpClient: OkHttpClient): CardService {
            return retrofit2.Retrofit.Builder()
                .baseUrl(Constants.backendUrl + "user/")
                .client(okHttpClient)
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                .build()
                .create(CardService::class.java)
        }
    }
}