package dev.vstd.shoppingcart.data.remote

import dev.vstd.shoppingcart.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface PaymentService {
    companion object {
        fun build(): PaymentService {
            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.backendUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(PaymentService::class.java)
        }
    }
}