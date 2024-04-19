package dev.vstd.shoppingcart.shopping.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dev.vstd.shoppingcart.auth.data.UserDao
import dev.vstd.shoppingcart.auth.data.UserEntity
import dev.vstd.shoppingcart.shopping.data.dao.CardDao
import dev.vstd.shoppingcart.shopping.data.dao.OrderDao
import dev.vstd.shoppingcart.shopping.data.dao.ProductDao
import dev.vstd.shoppingcart.shopping.data.entity.CardEntity
import dev.vstd.shoppingcart.shopping.data.entity.OrderEntity
import dev.vstd.shoppingcart.shopping.data.entity.ProductEntity

@Database(entities = [UserEntity::class, ProductEntity::class, CardEntity::class, OrderEntity::class], version = 1, exportSchema = false)
abstract class MockBackendDatabase: RoomDatabase() {
    abstract val productDao: ProductDao
    abstract val userDao: UserDao
    abstract val cardDao: CardDao
    abstract val orderDao: OrderDao

    companion object {
        fun create(context: Context): MockBackendDatabase {
            synchronized(this) {
                return Room.databaseBuilder(
                    context,
                    MockBackendDatabase::class.java,
                    "mock_backend.db"
                ).addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        db.execSQL("""
                            INSERT INTO UserEntity(id, username, password, email)
                            VALUES(1, 'admin', '12345678', 'admin@gmail.com')
                        """.trimIndent())
                    }
                })
                .build()
            }
        }
    }
}