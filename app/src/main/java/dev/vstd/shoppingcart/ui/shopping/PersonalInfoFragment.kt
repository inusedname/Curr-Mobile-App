package dev.vstd.shoppingcart.ui.shopping

import android.view.LayoutInflater
import android.view.ViewGroup
import dev.keego.shoppingcart.databinding.FragmentPersonalInfoBinding
import dev.vstd.shoppingcart.ui.base.BaseFragment

class PersonalInfoFragment: BaseFragment<FragmentPersonalInfoBinding>() {
    override fun onViewCreated(binding: FragmentPersonalInfoBinding) {
        TODO("Not yet implemented")
    }

    override val viewCreator: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPersonalInfoBinding
        get() = FragmentPersonalInfoBinding::inflate
}