package dev.vstd.shoppingcart.ui.order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import dev.keego.shoppingcart.databinding.FragmentOrdersBinding
import dev.vstd.shoppingcart.ui.base.BaseFragment
import dev.vstd.shoppingcart.ui.order.adapter.FragmentPageAdapter

class OrdersFragment : BaseFragment<FragmentOrdersBinding>() {
    private lateinit var adapter: FragmentPageAdapter

    override fun onViewCreated(binding: FragmentOrdersBinding) {
        initViews(binding)
    }

    private fun initViews(binding: FragmentOrdersBinding) {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        adapter = FragmentPageAdapter(childFragmentManager, lifecycle)
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Chờ xác nhận"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Chờ lấy hàng"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Chờ giao hàng"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Đã giao"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Đã hủy"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Trả hàng"))
        binding.viewPager.adapter = adapter
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(p0: TabLayout.Tab?) {
                if (p0 != null) {
                    binding.viewPager.currentItem = p0.position
                }
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabReselected(p0: TabLayout.Tab?) {
            }
        })
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })
    }

    override val viewCreator: (LayoutInflater, ViewGroup?, Boolean) -> FragmentOrdersBinding
        get() = FragmentOrdersBinding::inflate
}