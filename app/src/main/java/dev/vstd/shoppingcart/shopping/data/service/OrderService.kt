package dev.vstd.shoppingcart.shopping.data.service

import dev.vstd.shoppingcart.common.Constants
import dev.vstd.shoppingcart.shopping.data.entity.OrderEntity
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface OrderService {
    @POST("create")
    suspend fun makeOrder(@Body body: CreateOrderBodyDto): Response<OrderEntity>

    @GET("all")
    suspend fun getOrders(): Response<List<OrderEntity>>

    companion object {
        fun create(okHttpClient: OkHttpClient): OrderService {
            return Retrofit.Builder()
                .baseUrl(Constants.backendUrl + "order/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(OrderService::class.java)
        }
    }
}