package dev.vstd.shoppingcart.ui.shopping

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.keego.shoppingcart.R
import dev.keego.shoppingcart.databinding.FragmentPersonalInfoBinding
import dev.vstd.shoppingcart.Session
import dev.vstd.shoppingcart.dataMock.repository.UserRepository
import dev.vstd.shoppingcart.ui.base.BaseFragment
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PersonalInfoFragment: BaseFragment<FragmentPersonalInfoBinding>() {
    @Inject
    lateinit var userRepository: UserRepository

    override fun onViewCreated(binding: FragmentPersonalInfoBinding) {
        loadInfo(binding)
        binding.btnSeePayments.setOnClickListener {
            findNavController().navigate(R.id.action_personalInfoFragment_to_nav_payment_method)
        }
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun loadInfo(binding: FragmentPersonalInfoBinding) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                val user = userRepository.getUserInfo(Session.userEntity.value!!.id)
                if (user == null) {
                    Toast.makeText(context, "User not found", Toast.LENGTH_SHORT).show()
                } else {
                    binding.apply {
                        binding.apply {
                            username.text = user.username
                            email.text = user.email
                            balance.text = user.balance.toString()
                            address.text = user.address ?: "Not set"
                        }
                    }
                }
            }
        }
    }

    override val viewCreator: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPersonalInfoBinding
        get() = FragmentPersonalInfoBinding::inflate
}