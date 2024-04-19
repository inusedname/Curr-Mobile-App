package dev.vstd.shoppingcart.dataMock.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val username: String,
    val password: String,
    @ColumnInfo
    val email: String,
    val balance: Int = 1000,
    val address: String? = null,
)