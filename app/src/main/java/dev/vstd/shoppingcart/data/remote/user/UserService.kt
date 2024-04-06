package dev.vstd.shoppingcart.data.remote.user

import dev.vstd.shoppingcart.Constants
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {

    @POST("/user/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("/user/signup")
    suspend fun signup(@Body signupRequest: SignupRequest): Response<Unit>

    @GET("/user/payments")
    suspend fun getPaymentMethods(): Response<List<PaymentMethod>>

    @POST("/user/payments")
    suspend fun addPaymentMethod(): Response<PaymentMethod>

    companion object {
        fun build(): UserService {
            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(UserService::class.java)
        }
    }
}