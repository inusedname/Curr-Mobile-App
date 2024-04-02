package dev.vstd.shoppingcart.ui.setting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dev.keego.shoppingcart.R
import dev.keego.shoppingcart.databinding.FragmentSettingBinding
import dev.vstd.shoppingcart.ui.base.BaseFragment

class SettingFragment : BaseFragment<FragmentSettingBinding>() {
    override fun onViewCreated(binding: FragmentSettingBinding) {
        binding.btnShipAddress.setOnClickListener {
            navigateToFragmentUpdateAddress()
        }
    }

    override val viewCreator: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSettingBinding
        get() = FragmentSettingBinding::inflate

    private fun navigateToFragmentUpdateAddress() {
        findNavController().navigate(R.id.updateAddressFragment)
    }
}