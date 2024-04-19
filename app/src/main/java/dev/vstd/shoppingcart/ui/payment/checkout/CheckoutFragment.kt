package dev.vstd.shoppingcart.ui.payment.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import dev.keego.shoppingcart.R
import dev.vstd.shoppingcart.theme.ShoppingCartTheme
import dev.vstd.shoppingcart.ui.setting.UpdateAddressFragment

class CheckoutFragment : Fragment() {
    private lateinit var navControllerImpl: NavController
    private val vimel by viewModels<CheckoutVimel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        navControllerImpl = object : NavController(requireContext()) {
            override fun navigateUp(): Boolean {
                activity?.finish()
                return true
            }

            override fun navigate(resId: Int) {
                when (resId) {
                    R.id.action_checkoutFragment_to_updateAddressFragment2 -> {
                        findNavController().navigate(R.id.action_checkoutFragment_to_updateAddressFragment2)
                    }

                    R.id.action_checkoutFragment_to_paymentMethodsFragment -> {
                        findNavController().navigate(R.id.action_checkoutFragment_to_paymentMethodsFragment)
                    }

                    else -> {
                        super.navigate(resId)
                    }
                }
            }
        }

        setFragmentResultListener(UpdateAddressFragment.RESULT_OK) { _, bundle ->
            val address = bundle.getString(UpdateAddressFragment.EXTRA_FULL_ADDRESS)
            address?.let { vimel.setAddress(it) }
        }

        return ComposeView(requireContext()).apply {
            setContent {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                setContent {
                    ShoppingCartTheme {
                        checkout_(navController = navControllerImpl)
                    }
                }
            }
        }
    }
}