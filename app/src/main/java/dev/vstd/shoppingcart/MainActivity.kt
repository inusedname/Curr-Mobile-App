package dev.vstd.shoppingcart

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import dev.keego.shoppingcart.R
import dev.vstd.shoppingcart.ui.barcode.BarcodeActivity
import dev.vstd.shoppingcart.ui.payment.PaymentActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            Intent(this@MainActivity, PaymentActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}