package dev.vstd.shoppingcart.ui.order.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import dev.keego.shoppingcart.databinding.FragmentOrderListBinding
import dev.vstd.shoppingcart.ui.base.BaseFragment
import dev.vstd.shoppingcart.ui.order.adapter.Order
import dev.vstd.shoppingcart.ui.order.adapter.OrderAdapter

class OrderListFragment : BaseFragment<FragmentOrderListBinding>() {
    private val adapter by lazy {
        OrderAdapter()
    }

    override fun onViewCreated(binding: FragmentOrderListBinding) {
        binding.rvOrders.adapter = adapter
    }

    override val viewCreator: (LayoutInflater, ViewGroup?, Boolean) -> FragmentOrderListBinding
        get() = FragmentOrderListBinding::inflate

    fun setOrders(orders: List<Order>) {
        adapter.updateList(orders)
    }
}