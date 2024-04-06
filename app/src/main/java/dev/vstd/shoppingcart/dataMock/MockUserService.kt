package dev.vstd.shoppingcart.dataMock

import dev.vstd.shoppingcart.data.remote.user.*
import dev.vstd.shoppingcart.pref.AppPreferences
import retrofit2.Response

class MockUserService : UserService {
    override suspend fun login(loginRequest: LoginRequest): Response<LoginResponse> {
        AppPreferences.userCredential = "mock-user"
        return Response.success(
            LoginResponse(
                token = "1",
                username = "mock-user"
            )
        )
    }

    override suspend fun signup(signupRequest: SignupRequest): Response<Unit> {
        return Response.success(Unit)
    }

    override suspend fun addPaymentMethod(): Response<PaymentMethod> {
        return Response.success(
            PaymentMethod(
                id = 1,
                name = "Momo",
                imageAvatar = "https://cdn.haitrieu.com/wp-content/uploads/2022/10/Logo-MoMo-Square.png",
                imageCover = null,
                cardId = null,
                description = "Số dư: 1.232đ"
            )
        )
    }

    override suspend fun getPaymentMethods(): Response<List<PaymentMethod>> {
        return Response.success(
            listOf(
                PaymentMethod(
                    id = 1,
                    name = "Momo",
                    imageAvatar = "https://cdn.haitrieu.com/wp-content/uploads/2022/10/Logo-MoMo-Square.png",
                    imageCover = null,
                    cardId = null,
                    description = "Số dư: 1.232đ"
                )
            )
        )
    }
}