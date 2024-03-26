package dev.vstd.shoppingcart.ui.compare

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.keego.shoppingcart.R
import dev.keego.shoppingcart.databinding.CompareItemImageBinding
import dev.keego.shoppingcart.databinding.CompareItemImageDetailBinding
import dev.vstd.shoppingcart.ui.base.BaseFragment
import kotlin.math.log

class CompareItemImageDetail : BaseFragment<CompareItemImageDetailBinding>() {

    lateinit var imageId : Array<Int>
    lateinit var itemName : String
    lateinit var price : String
    lateinit var newArrayList : ArrayList<ItemImage>

    lateinit var recyclerView : RecyclerView

    override fun onViewCreated(binding: CompareItemImageDetailBinding) {
        imageId = Array(10) {R.drawable.image_phone}

        itemName = "iPhone 15 Pro Max"
        price = "19,500,000 đ"

        newArrayList = arrayListOf<ItemImage>()
        recyclerView = binding.compareItemImageDetail
        Log.d("TAG", "onViewCreated: CompareItemImageDetail")
        setData()
        binding.compareItemImageDetailName.text = itemName
        binding.compareItemImageDetailPrice.text = price
    }

    private fun setData() {
        for (i in imageId.indices) {
            val item = ItemImage(imageId[i])
            newArrayList.add(item)
        }
        recyclerView.adapter = CompareItemImageAdapter(newArrayList)
    }

    override val viewCreator: (LayoutInflater, ViewGroup?, Boolean) -> CompareItemImageDetailBinding
        get() = CompareItemImageDetailBinding::inflate
}