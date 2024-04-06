package dev.vstd.shoppingcart.data.remote.user

import com.google.gson.annotations.SerializedName

data class PaymentMethod(
    val id: Int,
    val name: String,

    @SerializedName("image_avatar")
    val imageAvatar: String?,

    @SerializedName("image_cover")
    val imageCover: Any?,

    @SerializedName("card_id")
    val cardId: String? = null,
    val description: String? = null,
)