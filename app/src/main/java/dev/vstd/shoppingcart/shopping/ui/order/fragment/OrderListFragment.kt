package dev.vstd.shoppingcart.shopping.ui.order.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dev.keego.shoppingcart.databinding.FragmentOrderListBinding
import dev.vstd.shoppingcart.common.ui.BaseFragment
import dev.vstd.shoppingcart.shopping.data.entity.OrderEntity
import dev.vstd.shoppingcart.shopping.data.repository.OrderRepository
import dev.vstd.shoppingcart.shopping.domain.Status
import dev.vstd.shoppingcart.shopping.ui.order.adapter.OrderAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class OrderListFragment(private val status: Status) : BaseFragment<FragmentOrderListBinding>() {
    @Inject
    lateinit var repository: OrderRepository

    private val adapter by lazy {
        OrderAdapter()
    }

    override fun onViewCreated(binding: FragmentOrderListBinding) {
        binding.rvOrders.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            val orders = repository.getAllOrders().filter { it.status == status }
            adapter.submitListt(orders.map(OrderEntity::toOrder))
        }
    }

    override val viewCreator: (LayoutInflater, ViewGroup?, Boolean) -> FragmentOrderListBinding
        get() = FragmentOrderListBinding::inflate
}