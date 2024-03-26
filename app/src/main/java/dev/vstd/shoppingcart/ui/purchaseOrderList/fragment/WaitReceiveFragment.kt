package dev.vstd.shoppingcart.ui.purchaseOrderList.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.keego.shoppingcart.R
import dev.keego.shoppingcart.databinding.FragmentWaitReceiveBinding
import dev.vstd.shoppingcart.ui.purchaseOrderList.adapter.ItemAdapter
import dev.vstd.shoppingcart.ui.purchaseOrderList.adapter.ItemDataClass

class WaitReceiveFragment : Fragment() {

    private lateinit var binding: FragmentWaitReceiveBinding
    lateinit var dataImageList: Array<Int>
    lateinit var titleList: Array<String>
    lateinit var nameList: Array<String>
    lateinit var descriptionList: Array<String>
    lateinit var priceList: Array<String>
    lateinit var newListItem: ArrayList<ItemDataClass>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWaitReceiveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
    }

    private fun setData() {
        dataImageList = Array(10) { R.drawable.image }
        titleList = Array(10){"TỔNG KHO LINH ĐÀM"}
        nameList = Array(10) {"Bột thông cống cực mạnh"}
        descriptionList = Array(10){"Hộp 250g"}
        priceList = Array(10){"đ17.000"}

        newListItem = arrayListOf<ItemDataClass>()

        for(i in dataImageList.indices){
            val item = ItemDataClass(dataImageList[i], titleList[i], nameList[i], descriptionList[i], priceList[i])
            newListItem.add(item)
        }

       val  itemAdapter = ItemAdapter(newListItem)
       binding.recycleView.apply {
           layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
           adapter = itemAdapter
       }
    }

}