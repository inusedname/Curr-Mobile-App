package dev.vstd.shoppingcart.dataMock

import dev.vstd.shoppingcart.data.remote.user.*
import dev.vstd.shoppingcart.pref.AppPreferences
import retrofit2.Response

class MockUserService : UserService {
    override suspend fun login(loginRequest: LoginRequest): Response<LoginResponse> {
        AppPreferences.userCredential = "mock-user"
        return Response.success(
            LoginResponse(
                message = "Login successfully!",
                token = "1",
                username = "mock-user"
            )
        )
    }

    override suspend fun signup(signupRequest: SignupRequest): Response<SignupResponse> {
        return Response.success(
            SignupResponse(
                message = "Signup successfully!"
            )
        )
    }

    override suspend fun getCard(): Response<CreditCard> {
        TODO("Not yet implemented")
    }

    override suspend fun addCard(): Response<CreditCard> {
        TODO("Not yet implemented")
    }
}