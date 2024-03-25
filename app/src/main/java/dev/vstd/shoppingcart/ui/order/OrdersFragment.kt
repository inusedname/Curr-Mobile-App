package dev.vstd.shoppingcart.ui.order

import android.view.LayoutInflater
import android.view.ViewGroup
import dev.keego.shoppingcart.databinding.FragmentOrdersBinding
import dev.vstd.shoppingcart.ui.base.BaseFragment

class OrdersFragment : BaseFragment<FragmentOrdersBinding>() {
    override fun onViewCreated(binding: FragmentOrdersBinding) {

    }

    override val viewCreator: (LayoutInflater, ViewGroup?, Boolean) -> FragmentOrdersBinding
        get() = FragmentOrdersBinding::inflate
}