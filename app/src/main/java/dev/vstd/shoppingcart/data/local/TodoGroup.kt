package dev.vstd.shoppingcart.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoGroup(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
)