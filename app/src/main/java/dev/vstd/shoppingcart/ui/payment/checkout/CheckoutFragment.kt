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
import androidx.navigation.findNavController
import dev.vstd.shoppingcart.theme.ShoppingCartTheme
import dev.vstd.shoppingcart.ui.setting.UpdateAddressFragment

class CheckoutFragment : Fragment() {
    private val vimel by viewModels<CheckoutVimel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        setFragmentResultListener(UpdateAddressFragment.RESULT_OK) { _, bundle ->
            val address = bundle.getString(UpdateAddressFragment.EXTRA_FULL_ADDRESS)
            address?.let { vimel.setAddress(it) }
        }

        return ComposeView(requireContext()).apply {
            setContent {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                setContent {
                    ShoppingCartTheme {
                        checkout_(navController = findNavController())
                    }
                }
            }
        }
    }
}