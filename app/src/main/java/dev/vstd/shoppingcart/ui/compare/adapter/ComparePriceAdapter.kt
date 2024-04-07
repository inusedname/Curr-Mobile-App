package dev.vstd.shoppingcart.ui.compare.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.keego.shoppingcart.databinding.ItemCardViewVerticalBinding
import dev.vstd.shoppingcart.data.remote.comparing.model.ComparingProduct

class ComparePriceAdapter(
    private val onBuyClick: (ComparingProduct) -> Unit
) : RecyclerView.Adapter<ComparePriceAdapter.MyViewHolder>() {
    private val products: MutableList<ComparingProduct> = mutableListOf()
    inner class MyViewHolder(private val binding: ItemCardViewVerticalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ComparingProduct) {
            binding.apply {
                Glide.with(itemView)
                    .load(product.image)
                    .into(binding.compareItemImageView)
                binding.compareItemNameView.text = product.title
                binding.compareItemPriceView.text = product.lowestPrice.toString() + "USD"
                binding.btnCheckSeller.setOnClickListener {
                    onBuyClick(product)
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: List<ComparingProduct>) {
        this.products.clear()
        this.products.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemCardViewVerticalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(products[position])
    }
}