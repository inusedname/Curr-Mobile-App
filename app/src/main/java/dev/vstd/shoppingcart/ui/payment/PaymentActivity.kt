package dev.vstd.shoppingcart.ui.payment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ramcosta.composedestinations.DestinationsNavHost
import dev.vstd.shoppingcart.ui.NavGraphs
import dev.vstd.shoppingcart.ui.payment.ui.theme.ShoppingCartTheme

class PaymentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingCartTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}