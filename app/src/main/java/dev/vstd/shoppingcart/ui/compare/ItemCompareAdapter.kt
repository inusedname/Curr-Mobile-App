package dev.vstd.shoppingcart.ui.compare

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.keego.shoppingcart.R

class ItemCompareAdapter(
    private val newList: ArrayList<Item>,
) :
    RecyclerView.Adapter<ItemCompareAdapter.MyViewHolder>() {

        var onItemClick: ((Item) -> Unit)? = null
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.image_item_view)
        val itemName: TextView = itemView.findViewById(R.id.name_item_view)
        val price: TextView = itemView.findViewById(R.id.price_item_view)
        val numberPlace: TextView = itemView.findViewById(R.id.quantity_item_view)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return newList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = newList[position]

        holder.image.setImageResource(currentItem.image)
        holder.itemName.text = currentItem.itemName
        holder.price.text = currentItem.price
        holder.numberPlace.text = currentItem.numberPlace

        holder.itemView.setOnClickListener{
            onItemClick?.invoke(currentItem)
        }
    }
}