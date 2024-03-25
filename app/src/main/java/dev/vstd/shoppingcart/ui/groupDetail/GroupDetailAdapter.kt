package dev.vstd.shoppingcart.ui.groupDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import dev.keego.shoppingcart.databinding.ItemTodoBinding
import dev.vstd.shoppingcart.data.local.TodoItem

class GroupDetailAdapter(
    private val onLongClickItem: (TodoItem) -> Unit,
    private val onCheck: (TodoItem) -> Unit,
) :
    ListAdapter<TodoItem, GroupDetailAdapter.GroupDetailVH>(MyDiffUtil()) {

    inner class GroupDetailVH(private val binding: ItemTodoBinding) : ViewHolder(binding.root) {
        fun bind(item: TodoItem) {
            binding.checkBox.apply {
                isChecked = item.isCompleted
                setOnClickListener {
                    onCheck(item)
                }
            }
            binding.title.text = item.title
            binding.root.setOnLongClickListener {
                onLongClickItem(item)
                true
            }
        }
    }

    private class MyDiffUtil: DiffUtil.ItemCallback<TodoItem>() {
        override fun areItemsTheSame(oldItem: TodoItem, newItem: TodoItem) = oldItem == newItem
        override fun areContentsTheSame(oldItem: TodoItem, newItem: TodoItem) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupDetailVH {
        return GroupDetailVH(
            ItemTodoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GroupDetailVH, position: Int) {
        holder.bind(getItem(position))
    }
}