package dev.vstd.shoppingcart.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoCategory(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
)