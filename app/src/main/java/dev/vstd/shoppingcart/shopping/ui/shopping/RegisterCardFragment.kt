package dev.vstd.shoppingcart.shopping.ui.shopping

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.keego.shoppingcart.databinding.FragmentRegisterCardBinding
import dev.vstd.shoppingcart.auth.Session
import dev.vstd.shoppingcart.auth.data.UserRepository
import dev.vstd.shoppingcart.common.ui.BaseFragment
import dev.vstd.shoppingcart.shopping.data.entity.CardEntity
import dev.vstd.shoppingcart.shopping.data.repository.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.UUID
import javax.inject.Inject

@AndroidEntryPoint
class RegisterCardFragment : BaseFragment<FragmentRegisterCardBinding>() {
    @Inject
    lateinit var userRepository: UserRepository

    override fun onViewCreated(binding: FragmentRegisterCardBinding) {
        val card = CardEntity(
            userId = Session.userEntity.value!!.id,
            cardNumber = UUID.randomUUID().toString().take(12),
            cardHolder = CardEntity.CardHolder.VISA,
            expirationDate = "12/25",
            cvv = "123",
            balance = 1000000
        )
        binding.apply {
            tvCVV.text = "CVV ${card.cvv}"
            tvCardNumber.text = card.cardNumber
            tvExpirationDate.text = "Exp ${card.expirationDate}"
        }
        binding.checkboxConfirm.setOnCheckedChangeListener { _, isChecked ->
            when (isChecked) {
                true -> binding.btnRegisterCard.isEnabled = true
                false -> binding.btnRegisterCard.isEnabled = false
            }
        }
        binding.checkboxConfirm.isChecked = false
        binding.btnRegisterCard.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val result = userRepository.registerCard(card)
                if (result.isSuccessful) {
                    Toast.makeText(context, "Card Register Success!", Toast.LENGTH_SHORT).show()
                    findNavController().navigateUp()
                } else {
                    Timber.e((result as Response.Failed).message)
                    Toast.makeText(context, "Card Register Failed!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override val viewCreator: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRegisterCardBinding
        get() = FragmentRegisterCardBinding::inflate
}