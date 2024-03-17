package dev.vstd.shoppingcart.data.remote

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface CheckoutService {
    @POST("/order")
    suspend fun makeOrder(@Body order: Order): Response<MakeOrderResp>

    suspend fun getUserInfo(): Response<UserInfo>

    suspend fun updateUserInfo(userInfo: UserInfo)

    // TODO: Su dung cookie/userid gan vao header de xac thuc nguoi dung

    companion object {
        private const val baseUrl = "https://api.stripe.com"

        fun build(): CheckoutService {
            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(CheckoutService::class.java)
        }
    }

}