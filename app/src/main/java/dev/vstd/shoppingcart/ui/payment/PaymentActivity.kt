package dev.vstd.shoppingcart.ui.payment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.keego.shoppingcart.R
import dev.vstd.shoppingcart.dataMock.MockUserService

class PaymentActivity : AppCompatActivity() {
    val userService = MockUserService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
    }
}