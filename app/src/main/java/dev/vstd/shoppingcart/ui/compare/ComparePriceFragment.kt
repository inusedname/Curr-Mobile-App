package dev.vstd.shoppingcart.ui.compare

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import dev.keego.shoppingcart.R
import dev.keego.shoppingcart.databinding.FragmentPriceCompareTabBinding
import dev.vstd.shoppingcart.ui.base.BaseFragment

class ComparePriceFragment : BaseFragment<FragmentPriceCompareTabBinding>() {

    lateinit var imageId : Array<Int>
    lateinit var itemName : Array<String>
    lateinit var price : Array<String>
    lateinit var numberPlace : Array<String>

    lateinit var newArrayList : ArrayList<Item>

    lateinit var recyclerView: RecyclerView
    override fun onViewCreated(binding: FragmentPriceCompareTabBinding) {
        Log.d("TAG", "onViewCreated: ComparePriceFragment")

        imageId = Array(10) {R.drawable.image_phone}
        itemName = Array(10) {"iPhone 15 Pro Max"}
        price = Array(10) {"19,500,000 đ"}
        numberPlace = Array(10) {"Có 110 nơi đang bán"}

        newArrayList = arrayListOf<Item>()

        recyclerView = binding.comparePriceRecyclerView
        setData()
    }

    private fun setData() {
        for (i in imageId.indices) {
            val item = Item(imageId[i], itemName[i], price[i], numberPlace[i])
            newArrayList.add(item)
        }

        recyclerView.adapter = ComparePriceAdapter(newArrayList)
    }

    override val viewCreator: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPriceCompareTabBinding
        get() = FragmentPriceCompareTabBinding::inflate
}