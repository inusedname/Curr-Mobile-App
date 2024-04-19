package dev.vstd.shoppingcart.ui.payment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.keego.shoppingcart.databinding.ItemPaymentMethodBinding
import dev.vstd.shoppingcart.domain.PaymentMethod
import dev.vstd.shoppingcart.ui.base.DiffUtils

class PaymentMethodsAdapter :
    ListAdapter<PaymentMethod, PaymentMethodsAdapter.ViewHolder>(DiffUtils.any<PaymentMethod>()) {
    class ViewHolder(private val binding: ItemPaymentMethodBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(paymentMethod: PaymentMethod) {
            Glide.with(binding.root.context)
                .load(paymentMethod.type.imageUrl)
                .centerCrop()
                .into(binding.purchaseIcon)
            binding.purchaseName.text = paymentMethod.type.name
            binding.purchaseDesc.text = paymentMethod.textDescription
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