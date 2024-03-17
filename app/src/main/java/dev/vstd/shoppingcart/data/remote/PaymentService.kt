package dev.vstd.shoppingcart.data.remote

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface PaymentService {
    suspend fun createCreditCard(): Response<CreditCard>
    fun getCreditCards(): Response<List<CreditCard>>

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