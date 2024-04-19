package dev.vstd.shoppingcart.shopping.ui.payment.checkout

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.keego.shoppingcart.R
import dev.keego.shoppingcart.databinding.FragmentAskForCreditCardCredentialBinding
import dev.vstd.shoppingcart.auth.Session
import dev.vstd.shoppingcart.auth.data.UserRepository
import dev.vstd.shoppingcart.common.ui.BaseFragment
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
                    val cards = userRepository.getCards(Session.userEntity.value!!.id)
                    cards.firstOrNull()?.let {
                        if (it.cvv == binding.etCreditCardCVV.text.toString()) {
                            userRepository.cashOut(
                                Session.userEntity.value!!.id,
                                checkoutVimel.total.value
                            )
                            Toast.makeText(requireContext(), "Payment success", Toast.LENGTH_SHORT)
                                .show()
                            findNavController().popBackStack(
                                R.id.checkoutFragment,
                                false
                            )
                        } else {
                            Toast.makeText(requireContext(), "Invalid CVV", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }
    }

    override val viewCreator: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAskForCreditCardCredentialBinding
        get() = FragmentAskForCreditCardCredentialBinding::inflate
}