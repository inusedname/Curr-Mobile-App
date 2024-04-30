package dev.vstd.shoppingcart.shopping.ui.payment.checkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.keego.shoppingcart.R
import dev.keego.shoppingcart.databinding.FragmentAskForCreditCardCredentialBinding
import dev.vstd.shoppingcart.auth.data.UserRepository
import dev.vstd.shoppingcart.common.ui.BaseFragment
import dev.vstd.shoppingcart.common.utils.toast
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class AskForCreditCardCredentialFragment :
    BaseFragment<FragmentAskForCreditCardCredentialBinding>() {
    @Inject
    lateinit var userRepository: UserRepository

    private val checkoutVimel by activityViewModels<CheckoutVimel>()

    override fun onViewCreated(binding: FragmentAskForCreditCardCredentialBinding) {
        binding.etCreditCardCVV.addTextChangedListener { editable ->
            binding.btnNext.isEnabled = editable!!.isNotEmpty() && editable.length == 3
            binding.btnNext.setOnClickListener {
                lifecycleScope.launch {
                    val cvv = binding.etCreditCardCVV.text.toString()
                    val verifyResponse = userRepository.validateCvv(cvv)
                    if (verifyResponse.isSuccessful) {
                        requireContext().toast("Invalid CVV")
                        return@launch
                    }
                    requireContext().toast("Payment success!")
                    findNavController().popBackStack(
                        R.id.checkoutFragment,
                        false
                    )
                }
            }
        }
    }

    override val viewCreator: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAskForCreditCardCredentialBinding
        get() = FragmentAskForCreditCardCredentialBinding::inflate
}