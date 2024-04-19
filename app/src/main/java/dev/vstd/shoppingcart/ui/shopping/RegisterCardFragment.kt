package dev.vstd.shoppingcart.ui.shopping

import android.view.LayoutInflater
import android.view.ViewGroup
import dev.keego.shoppingcart.databinding.FragmentRegisterCardBinding
import dev.vstd.shoppingcart.ui.base.BaseFragment

class RegisterCardFragment: BaseFragment<FragmentRegisterCardBinding>() {
    override fun onViewCreated(binding: FragmentRegisterCardBinding) {

    }

    override val viewCreator: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRegisterCardBinding
        get() = FragmentRegisterCardBinding::inflate
}