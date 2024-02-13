package dev.vstd.shoppingcart.data.local

import androidx.room.*

@Dao
interface TodoCategoryDao {
    @Insert
    suspend fun insert(todoCategory: TodoCategory): Long

    @Update
    suspend fun update(todoCategory: TodoCategory)

    @Delete
    suspend fun delete(todoCategory: TodoCategory)

    @Query("SELECT * FROM TodoCategory")
    suspend fun getAll(): List<TodoCategory>
}