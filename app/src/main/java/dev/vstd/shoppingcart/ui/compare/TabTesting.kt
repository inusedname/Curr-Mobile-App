package dev.vstd.shoppingcart.ui.compare

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import dev.keego.shoppingcart.databinding.FragmentItemsTabBinding
import dev.vstd.shoppingcart.ui.base.BaseFragment

class TabTesting : BaseFragment<FragmentItemsTabBinding>() {

    private lateinit var tabLayout : TabLayout
    private lateinit var viewPager2 : ViewPager2
    private lateinit var adapter : FragmentPageAdapter

    override fun onViewCreated(binding: FragmentItemsTabBinding) {
//        tabLayout = binding.tabLayout
//        viewPager2 = binding.viewPager2
//
//
//
//        Log.i("TabTesting", "onViewCreated: asdf")
//
////        adapter = FragmentPageAdapter(suppo, lifecycle)
//        tabLayout.addTab(tabLayout.newTab().setText("Price"))
//        tabLayout.addTab(tabLayout.newTab().setText("Detail"))
//        tabLayout.addTab(tabLayout.newTab().setText("Specs"))
//
//        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                if (tab != null) viewPager2.currentItem = tab.position
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onTabReselected(tab: TabLayout.Tab?) {
//                TODO("Not yet implemented")
//            }
//
//        })
//
//        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//                tabLayout.selectTab(tabLayout.getTabAt(position))
//            }
//
//        })
    }

    override val viewCreator: (LayoutInflater, ViewGroup?, Boolean) -> FragmentItemsTabBinding
        get() = FragmentItemsTabBinding::inflate
}