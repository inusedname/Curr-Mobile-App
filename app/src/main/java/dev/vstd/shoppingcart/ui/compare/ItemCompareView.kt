package dev.vstd.shoppingcart.ui.compare

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import dev.keego.shoppingcart.R
import dev.keego.shoppingcart.databinding.ItemCompareViewBinding
import dev.vstd.shoppingcart.ui.base.BaseFragment
import kotlin.math.log

class ItemCompareView : BaseFragment<ItemCompareViewBinding>() {

    lateinit var imageId: Array<Int>
    lateinit var itemName: Array<String>
    lateinit var price: Array<String>
    lateinit var numberPlace: Array<String>

    lateinit var newArrayList: ArrayList<Item>

    lateinit var recyclerView: RecyclerView

    override fun onViewCreated(binding: ItemCompareViewBinding) {
        Log.d("TAG", "onViewCreated: ComparePriceFragment")
        imageId = Array(10) { R.drawable.image_phone }
        itemName = Array(10) { "iPhone 15 Pro Max" }
        price = Array(10) { "19,500,000 đ" }
        numberPlace = Array(10) { "Có 110 nơi đang bán" }

        newArrayList = arrayListOf<Item>()

        recyclerView = binding.compareRecyclerView
        setData()

    }


    private fun setData() {
        for (i in imageId.indices) {
            val item = Item(imageId[i], itemName[i], price[i], numberPlace[i])
            newArrayList.add(item)
        }

        recyclerView.adapter = ItemCompareAdapter(newArrayList)
    }

    override val viewCreator: (LayoutInflater, ViewGroup?, Boolean) -> ItemCompareViewBinding
        get() = ItemCompareViewBinding::inflate
}