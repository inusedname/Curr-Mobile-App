package dev.vstd.shoppingcart.ui.compare

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import dev.keego.shoppingcart.databinding.FragmentComparingBinding
import dev.vstd.shoppingcart.ui.base.BaseFragment

class ComparingFragment : BaseFragment<FragmentComparingBinding>() {
    private lateinit var tabLayout : TabLayout
    private lateinit var viewPager2 : ViewPager2
    private lateinit var adapter : FragmentPageAdapter

    override fun onViewCreated(binding: FragmentComparingBinding) {
        tabLayout = binding.tabLayout
        viewPager2 = binding.viewPager2

        adapter = FragmentPageAdapter(childFragmentManager, lifecycle)
        viewPager2.adapter = adapter

        tabLayout.addTab(tabLayout.newTab().setText("Price"))
        tabLayout.addTab(tabLayout.newTab().setText("Detail"))
        tabLayout.addTab(tabLayout.newTab().setText("Specs"))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) viewPager2.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }

        })
    }

    override val viewCreator: (LayoutInflater, ViewGroup?, Boolean) -> FragmentComparingBinding
        get() = FragmentComparingBinding::inflate
}