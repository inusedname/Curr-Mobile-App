package dev.vstd.shoppingcart.ui.shopping

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.keego.shoppingcart.R
import dev.keego.shoppingcart.databinding.FragmentPaymentMethodsBinding
import dev.vstd.shoppingcart.dataMock.repository.UserRepository
import dev.vstd.shoppingcart.domain.PaymentMethod
import dev.vstd.shoppingcart.ui.base.BaseFragment
import dev.vstd.shoppingcart.utils.beGone
import dev.vstd.shoppingcart.utils.beVisible
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PaymentMethodsFragment : BaseFragment<FragmentPaymentMethodsBinding>() {
    @Inject
    lateinit var userRepository: UserRepository

    private val cards = MutableStateFlow(listOf<PaymentMethod>())

    override fun onViewCreated(binding: FragmentPaymentMethodsBinding) {
        binding.sectionAddNewCard.apply {
            purchaseName.text = "Register your new card now"
            purchaseDesc.text = "Click here!"
            purchaseIcon.setImageDrawable(
                ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.ic_add_card,
                    null
                )
            )
            root.setOnClickListener {
                findNavController().navigate(R.id.action_paymentMethodsFragment_to_registerCardFragment)
            }
            root.beGone() // hide it
        }

        setOnClicks(binding)
        observeState(binding)

        fetchData(binding)
    }

    private fun fetchData(binding: FragmentPaymentMethodsBinding) {
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
            if (response.isEmpty()) {
                binding.sectionAddNewCard.root.beVisible()
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