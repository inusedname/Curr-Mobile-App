package dev.vstd.shoppingcart.ui.order.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import dev.keego.shoppingcart.R
import dev.vstd.shoppingcart.ui.order.fragment.OrderListFragment

class FragmentPageAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 6
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = OrderListFragment()
        fragment.setOrders(getFakeOrders())
        return fragment
    }

    private fun getFakeOrders(): List<Order> {
        val orders = mutableListOf<Order>()
        for (i in 1..10) {
            orders.add(
                Order(
                    image = R.drawable.image,
                    title = "TỔNG KHO LINH ĐÀM",
                    name = "Bột thông cống cực mạnh",
                    description = "Hộp 250g",
                    price = "đ17.000"
                )
            )
        }
        return orders
    }
}