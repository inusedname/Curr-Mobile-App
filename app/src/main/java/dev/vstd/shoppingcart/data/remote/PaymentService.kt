package dev.vstd.shoppingcart.data.remote

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface PaymentService {

    fun getUserCreditCards(): Response<CreditCard?>

    fun createCreditCard(): Response<CreditCard>

    fun makeOrder(order: Order)

    fun getUserInfo(): Response<UserInfo>

    fun updateUserInfo(userInfo: UserInfo)

    // TODO: Su dung cookie/userid gan vao header de xac thuc nguoi dung

    companion object {
        private const val baseUrl = "https://api.stripe.com"

        fun build(): PaymentService {
            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(PaymentService::class.java)
        }
    }

}