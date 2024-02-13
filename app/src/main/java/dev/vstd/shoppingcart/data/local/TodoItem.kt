package dev.vstd.shoppingcart.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: Int,
    val categoryId: Int,
    val isCompleted: Boolean,
)