package dev.vstd.shoppingcart.ui.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ramcosta.composedestinations.DestinationsNavHost
import dev.vstd.shoppingcart.data.remote.user.UserService
import dev.vstd.shoppingcart.ui.NavGraphs
import dev.vstd.shoppingcart.ui.payment.ui.theme.ShoppingCartTheme

/**
 * Login, signup
 */
class AuthActivity : ComponentActivity() {
    val userService = UserService.build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingCartTheme {
                DestinationsNavHost(navGraph = NavGraphs.auth)
            }
        }
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, AuthActivity::class.java))
        }
    }
}