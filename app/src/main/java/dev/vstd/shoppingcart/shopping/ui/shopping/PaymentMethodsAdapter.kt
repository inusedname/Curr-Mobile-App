package dev.vstd.shoppingcart.shopping.ui.shopping

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.keego.shoppingcart.databinding.ItemPaymentMethodBinding
import dev.vstd.shoppingcart.common.ui.DiffUtils
import dev.vstd.shoppingcart.shopping.domain.PaymentMethod

class PaymentMethodsAdapter(private val onClick: (PaymentMethod) -> Unit) :
    ListAdapter<PaymentMethod, PaymentMethodsAdapter.ViewHolder>(DiffUtils.any<PaymentMethod>()) {
    inner class ViewHolder(private val binding: ItemPaymentMethodBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(paymentMethod: PaymentMethod) {
            Glide.with(binding.root.context)
                .load(paymentMethod.type.imageUrl)
                .into(binding.purchaseIcon)
            binding.purchaseName.text = paymentMethod.type.name
            binding.purchaseDesc.text = paymentMethod.textDescription
            binding.root.setOnClickListener {
                onClick(paymentMethod)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPaymentMethodBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}