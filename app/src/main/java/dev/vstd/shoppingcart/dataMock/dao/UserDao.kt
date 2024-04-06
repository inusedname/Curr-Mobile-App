package dev.vstd.shoppingcart.dataMock.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import dev.vstd.shoppingcart.dataMock.entity.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE email = :email")
    fun getByEmail(email: String): User?

    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)
}