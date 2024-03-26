package dev.vstd.shoppingcart.ui.compare

import android.view.LayoutInflater
import android.view.ViewGroup
import dev.keego.shoppingcart.databinding.FragmentSpecsTabBinding
import dev.vstd.shoppingcart.ui.base.BaseFragment

class SpecsFragment : BaseFragment<FragmentSpecsTabBinding>(){
    override fun onViewCreated(binding: FragmentSpecsTabBinding) {

    }

    override val viewCreator: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSpecsTabBinding
        get() = FragmentSpecsTabBinding::inflate

}