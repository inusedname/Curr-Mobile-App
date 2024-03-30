package dev.vstd.shoppingcart.data

import retrofit2.Response

object APIErrorUtil {
    fun getAPIError(response: Response<*>): APIError {
        val message = response.errorBody()!!.string()
        return APIError(response.code(), message)
    }
}