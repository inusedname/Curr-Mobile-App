package dev.vstd.shoppingcart.ui.payment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import dev.keego.shoppingcart.databinding.FragmentPaymentMethodsBinding
import dev.vstd.shoppingcart.data.remote.user.PaymentMethod
import dev.vstd.shoppingcart.ui.base.BaseFragment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PaymentMethodsFragment : BaseFragment<FragmentPaymentMethodsBinding>() {
    private val cards = MutableStateFlow(listOf<PaymentMethod>())

    override fun onViewCreated(binding: FragmentPaymentMethodsBinding) {
        setOnClicks(binding)
        observeState(binding)

        fetchData()
    }

    private fun fetchData() {
        val userService = (this.activity as PaymentActivity).userService
        viewLifecycleOwner.lifecycleScope.launch {
            val response = userService.getPaymentMethods()
            if (response.isSuccessful) {
                cards.value = response.body()!!
            }
        }
    }

    private fun observeState(binding: FragmentPaymentMethodsBinding) {
        viewLifecycleOwner.lifecycleScope.launch {
            launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    cards.collect {
                        (binding.rvAccountsAndCards.adapter as PaymentMethodsAdapter).submitList(it)
                    }
                }
            }
        }
    }

    private fun setOnClicks(binding: FragmentPaymentMethodsBinding) {
        binding.rvAccountsAndCards.adapter = PaymentMethodsAdapter()
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    override val viewCreator: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPaymentMethodsBinding
        get() = FragmentPaymentMethodsBinding::inflate
}