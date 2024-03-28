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
    suspend fun signup(@Body signupRequest: SignupRequest): Response<SignupResponse>

    @GET("/user/card")
    suspend fun getCard(): Response<CreditCard>

    @POST("/user/card")
    suspend fun addCard(): Response<CreditCard>

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