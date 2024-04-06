package dev.vstd.shoppingcart.dataMock

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.vstd.shoppingcart.dataMock.dao.ProductDao
import dev.vstd.shoppingcart.dataMock.dao.UserDao
import dev.vstd.shoppingcart.dataMock.entity.Product
import dev.vstd.shoppingcart.dataMock.entity.User

@Database(entities = [User::class, Product::class], version = 1)
abstract class MockBackendDatabase: RoomDatabase() {
    abstract val productDao: ProductDao
    abstract val userDao: UserDao

    companion object {
        fun create(context: Context): MockBackendDatabase {
            synchronized(this) {
                return Room.databaseBuilder(
                    context,
                    MockBackendDatabase::class.java,
                    "mock_backend.db"
                ).build()
            }
        }
    }
}