package dev.vstd.shoppingcart.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TodoGroup::class, TodoItem::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract val todoGroupDao: TodoGroupDao
    abstract val todoItemDao: TodoItemDao

    companion object {
        fun createDatabase(context: Context): AppDatabase {
            // synchronized
            return synchronized(this) {
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "todo.db"
                ).build()
            }
        }
    }
}