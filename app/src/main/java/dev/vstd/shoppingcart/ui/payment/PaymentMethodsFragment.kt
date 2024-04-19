package dev.vstd.shoppingcart.ui.payment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import dev.keego.shoppingcart.databinding.FragmentPaymentMethodsBinding
import dev.vstd.shoppingcart.domain.PaymentMethod
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
        val userRepository = (this.activity as PaymentActivity).userRepository
        viewLifecycleOwner.lifecycleScope.launch {
            val methods = mutableListOf(
                PaymentMethod(type = PaymentMethod.Type.MOMO, "Số dư: 1.832đ"),
                PaymentMethod(type = PaymentMethod.Type.COD, "Chuyển khoản khi nhận hàng"),
            )
            val response =
                userRepository.getCards(dev.vstd.shoppingcart.Session.userEntity.value!!.id)
            for (item in response) {
                methods.add(
                    PaymentMethod(
                        type = PaymentMethod.Type.CREDIT_CARD,
                        "**** **** **** ${item.cardNumber.takeLast(4)}"
                    )
                )
            }
            cards.value = methods
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