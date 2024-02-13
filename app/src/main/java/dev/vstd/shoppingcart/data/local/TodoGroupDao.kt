package dev.vstd.shoppingcart.data.local

import androidx.room.*

@Dao
interface TodoGroupDao {
    @Insert
    suspend fun insert(todoGroup: TodoGroup): Long

    @Update
    suspend fun update(todoGroup: TodoGroup)

    @Delete
    suspend fun delete(todoGroup: TodoGroup)

    @Query("SELECT * FROM TodoGroup")
    suspend fun getAll(): List<TodoGroup>

    @Query("SELECT * FROM TodoGroup WHERE id = :id")
    suspend fun getById(id: Int): TodoGroup
}