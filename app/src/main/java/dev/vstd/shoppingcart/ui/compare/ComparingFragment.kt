package dev.vstd.shoppingcart.ui.compare

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import dev.keego.shoppingcart.databinding.FragmentComparingBinding
import dev.vstd.shoppingcart.ui.base.BaseFragment
import dev.vstd.shoppingcart.ui.payment.PaymentActivity

class ComparingFragment : BaseFragment<FragmentComparingBinding>() {

    override fun onViewCreated(binding: FragmentComparingBinding) {
        binding.fabCheckout.setOnClickListener {
            context?.let {
                Intent(it, PaymentActivity::class.java).apply {
                    startActivity(this)
                }
            }
        }
    }

    override val viewCreator: (LayoutInflater, ViewGroup?, Boolean) -> FragmentComparingBinding
        get() = FragmentComparingBinding::inflate
}