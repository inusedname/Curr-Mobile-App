package dev.vstd.shoppingcart.ui.purchaseOrderList.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import dev.keego.shoppingcart.R
import dev.keego.shoppingcart.databinding.FragmentWaitDeliveryBinding
import dev.vstd.shoppingcart.ui.purchaseOrderList.adapter.ItemAdapter
import dev.vstd.shoppingcart.ui.purchaseOrderList.adapter.ItemDataClass

class WaitDeliveryFragment : Fragment() {

    private lateinit var binding: FragmentWaitDeliveryBinding
    lateinit var dataImageList: List<Int>
    lateinit var titleList: List<String>
    lateinit var nameList: List<String>
    lateinit var descriptionList: List<String>
    lateinit var priceList: List<String>
    lateinit var newListItem: MutableList<ItemDataClass>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentWaitDeliveryBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
    }

    private fun setData() {
        dataImageList = List(10) { R.drawable.image }
        titleList = List(10) { "TỔNG KHO LINH ĐÀM" }
        nameList = List(10) { "Bột thông cống cực mạnh" }
        descriptionList = List(10) { "Hộp 250g" }
        priceList = List(10) { "đ17.000" }

        newListItem = arrayListOf<ItemDataClass>()

        for (i in dataImageList.indices) {
            val item = ItemDataClass(
                dataImageList[i],
                titleList[i],
                nameList[i],
                descriptionList[i],
                priceList[i]
            )
            newListItem.add(item)
        }

        val itemAdapter = ItemAdapter(newListItem)
        binding.recycleView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            adapter = itemAdapter
        }
    }


}