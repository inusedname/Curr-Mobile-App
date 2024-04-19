package dev.vstd.shoppingcart.pricecompare.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.keego.shoppingcart.databinding.ItemSellerBinding
import dev.vstd.shoppingcart.pricecompare.data.model.SellerInfo

class SellerAdapter(
    private val onClick: (SellerInfo) -> Unit
): RecyclerView.Adapter<SellerAdapter.ViewHolder>() {
    private val sellers = mutableListOf<SellerInfo>()
    private var productLogo: String? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(sellers: List<SellerInfo>, productLogo: String?) {
        this.productLogo = productLogo
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
            Glide.with(binding.root)
                .load(sellerInfo.sellerLogo)
                .into(binding.ivSeller)
            productLogo?.let {
                Glide.with(binding.root)
                    .load(it)
                    .into(binding.ivProduct)
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