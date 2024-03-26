package dev.vstd.shoppingcart.ui.order.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import dev.vstd.shoppingcart.ui.order.fragment.CancelledFragment
import dev.vstd.shoppingcart.ui.order.fragment.DeliveredFragment
import dev.vstd.shoppingcart.ui.order.fragment.ReturnedFragment
import dev.vstd.shoppingcart.ui.order.fragment.WaitConfirmFragment
import dev.vstd.shoppingcart.ui.order.fragment.WaitDeliveryFragment
import dev.vstd.shoppingcart.ui.order.fragment.WaitReceiveFragment

class FragmentPageAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 6
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> WaitConfirmFragment()
            1 -> WaitReceiveFragment()
            2 -> WaitDeliveryFragment()
            3 -> DeliveredFragment()
            4 -> CancelledFragment()
            else -> ReturnedFragment()
        }
    }

}