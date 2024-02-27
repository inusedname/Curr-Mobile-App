package dev.vstd.shoppingcart.ui.groups

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.keego.shoppingcart.databinding.ItemGroupsBinding
import dev.vstd.shoppingcart.data.local.TodoGroup

class GroupsAdapter(private val onClick: (TodoGroup) -> Unit): ListAdapter<TodoGroup, GroupsAdapter.ViewHolder>(MyDiffUtil) {
    inner class ViewHolder(private val binding: ItemGroupsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TodoGroup) {
            binding.root.setOnClickListener {
                onClick(item)
            }
            binding.title.text = item.title
        }
    }

    private object MyDiffUtil: DiffUtil.ItemCallback<TodoGroup>() {
        override fun areItemsTheSame(oldItem: TodoGroup, newItem: TodoGroup) = oldItem == newItem
        override fun areContentsTheSame(oldItem: TodoGroup, newItem: TodoGroup) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemGroupsBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}