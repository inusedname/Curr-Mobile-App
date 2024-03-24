package dev.vstd.shoppingcart.ui.compare

import android.view.LayoutInflater
import android.view.ViewGroup
import dev.keego.shoppingcart.databinding.FragmentComparingBinding
import dev.vstd.shoppingcart.ui.base.BaseFragment

class ComparingFragment : BaseFragment<FragmentComparingBinding>() {
    override fun onViewCreated(binding: FragmentComparingBinding) {

    }

    override val viewCreator: (LayoutInflater, ViewGroup?, Boolean) -> FragmentComparingBinding
        get() = FragmentComparingBinding::inflate
}