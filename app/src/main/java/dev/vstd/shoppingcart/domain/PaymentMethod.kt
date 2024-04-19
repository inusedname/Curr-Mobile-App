package dev.vstd.shoppingcart.domain

data class PaymentMethod(
    val type: Type,
    val textDescription: String,
) {
    enum class Type(val imageUrl: String, val title: String) {
        CREDIT_CARD(
            imageUrl = "https://th.bing.com/th/id/OIP.xVREsbEnxpFwYsgl4hNO7QHaDA?rs=1&pid=ImgDetMain",
            title = "Credit Card"
        ),
        MOMO(
            imageUrl = "https://th.bing.com/th/id/OIP.gG0oc_UlphEFcMgmusdb6gHaHa?rs=1&pid=ImgDetMain",
            title = "Momo"
        ),
        COD(
            imageUrl = "https://img0.etsystatic.com/148/0/10872947/il_340x270.1101464894_r87w.jpg",
            title = "Cash On Delivery"
        ),
    }
}