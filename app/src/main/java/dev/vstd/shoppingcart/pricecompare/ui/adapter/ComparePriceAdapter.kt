package dev.vstd.shoppingcart.pricecompare.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.keego.shoppingcart.databinding.ItemViewBinding
import dev.vstd.shoppingcart.pricecompare.data.model.ComparingProduct

class ComparePriceAdapter(
    private val onBuyClick: (ComparingProduct) -> Unit
) : RecyclerView.Adapter<ComparePriceAdapter.MyViewHolder>() {
    private val products: MutableList<ComparingProduct> = mutableListOf()
    inner class MyViewHolder(private val binding: ItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ComparingProduct) {
            binding.apply {
                Glide.with(itemView)
                    .load(product.image)
                    .into(binding.ivProduct)
                binding.tvTitle.text = product.title
                binding.tvPrice.text = product.lowestPrice.toString() + "USD"
                binding.root.setOnClickListener {
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
            ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(products[position])
    }
}