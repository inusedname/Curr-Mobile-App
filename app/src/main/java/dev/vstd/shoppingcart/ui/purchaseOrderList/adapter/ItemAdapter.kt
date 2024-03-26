package dev.vstd.shoppingcart.ui.purchaseOrderList.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.keego.shoppingcart.R

class ItemAdapter(private val dataList : ArrayList<ItemDataClass>): RecyclerView.Adapter<ItemAdapter.ViewHolderClass>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_purchase_order_list,parent,false)
        return ViewHolderClass(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem =dataList[position]
        holder.imageViewItemLayout.setImageResource(currentItem.dataImage)
        holder.tvTitleItemLayout.text = currentItem.title
        holder.tvNameItemLayout.text = currentItem.name
        holder.tvDescriptionItemLayout.text = currentItem.description
        holder.tvPriceItemLayout.text = currentItem.price

    }

    class ViewHolderClass(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageViewItemLayout: ImageView = itemView.findViewById(R.id.imageViewItemLayout)
        val tvTitleItemLayout: TextView = itemView.findViewById(R.id.tvTitleItemLayout)
        val tvNameItemLayout: TextView = itemView.findViewById(R.id.tvNameItemLayout)
        val tvDescriptionItemLayout: TextView = itemView.findViewById(R.id.tvDescriptionItemLayout)
        val tvPriceItemLayout: TextView = itemView.findViewById(R.id.tvPriceItemLayout)
    }
}