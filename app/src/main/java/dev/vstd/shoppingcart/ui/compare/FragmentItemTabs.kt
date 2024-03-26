package dev.vstd.shoppingcart.ui.compare

import android.view.LayoutInflater
import android.view.ViewGroup
import dev.keego.shoppingcart.databinding.FragmentItemsTabBinding
import dev.vstd.shoppingcart.ui.base.BaseFragment

class FragmentItemTabs : BaseFragment<FragmentItemsTabBinding>() {
    override fun onViewCreated(binding: FragmentItemsTabBinding) {

    }

    override val viewCreator: (LayoutInflater, ViewGroup?, Boolean) -> FragmentItemsTabBinding
        get() = FragmentItemsTabBinding::inflate
}