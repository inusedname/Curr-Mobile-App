package dev.vstd.shoppingcart.data

import com.google.gson.Gson
import retrofit2.Response

object APIErrorUtil {
    fun getAPIError(response: Response<*>): APIError {
        return Gson().fromJson(response.errorBody()!!.string(), APIError::class.java)
    }
}