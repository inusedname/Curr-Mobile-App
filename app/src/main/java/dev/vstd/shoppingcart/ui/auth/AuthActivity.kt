package dev.vstd.shoppingcart.ui.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint
import dev.vstd.shoppingcart.dataMock.repository.UserRepository
import dev.vstd.shoppingcart.theme.ShoppingCartTheme
import javax.inject.Inject

/**
 * Login, signup
 */
@AndroidEntryPoint
class AuthActivity : ComponentActivity() {
    @Inject
    lateinit var userRepository: UserRepository

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