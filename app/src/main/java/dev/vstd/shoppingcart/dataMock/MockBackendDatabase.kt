package dev.vstd.shoppingcart.dataMock

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.vstd.shoppingcart.dataMock.dao.ProductDao
import dev.vstd.shoppingcart.dataMock.dao.UserDao
import dev.vstd.shoppingcart.dataMock.entity.CardEntity
import dev.vstd.shoppingcart.dataMock.entity.OrderEntity
import dev.vstd.shoppingcart.dataMock.entity.ProductEntity
import dev.vstd.shoppingcart.dataMock.entity.UserEntity

@Database(entities = [UserEntity::class, ProductEntity::class, CardEntity::class, OrderEntity::class], version = 1)
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