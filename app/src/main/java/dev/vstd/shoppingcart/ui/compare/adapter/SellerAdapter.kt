package dev.vstd.shoppingcart.ui.compare.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.keego.shoppingcart.databinding.ItemSellerBinding
import dev.vstd.shoppingcart.data.remote.comparing.model.SellerInfo

class SellerAdapter(private val onClick: (SellerInfo) -> Unit): RecyclerView.Adapter<SellerAdapter.ViewHolder>() {
    private val sellers = mutableListOf<SellerInfo>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(sellers: List<SellerInfo>) {
        this.sellers.clear()
        this.sellers.addAll(sellers)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemSellerBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(sellerInfo: SellerInfo) {
            binding.price.text = sellerInfo.price
            binding.root.setOnClickListener {
                onClick(sellerInfo)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSellerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return sellers.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(sellers[position])
    }
}