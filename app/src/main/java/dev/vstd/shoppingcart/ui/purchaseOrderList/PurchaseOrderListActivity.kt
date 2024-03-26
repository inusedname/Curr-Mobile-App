package dev.vstd.shoppingcart.ui.purchaseOrderList

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import dev.keego.shoppingcart.databinding.ActivityPurchaseOrderListBinding
import dev.vstd.shoppingcart.ui.purchaseOrderList.adapter.FragmentPageAdapter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PurchaseOrderListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPurchaseOrderListBinding
    private lateinit var adapter: FragmentPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initViews()
    }

    private fun initViews() {
        binding.btnBack.setOnClickListener {
            GlobalScope.launch {
                Intent(this@PurchaseOrderListActivity, MainActivity::class.java).also {
                    startActivity(it)
                }
            }
        }

        adapter = FragmentPageAdapter(supportFragmentManager, lifecycle)
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

    private fun initBinding() {
        binding = ActivityPurchaseOrderListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}