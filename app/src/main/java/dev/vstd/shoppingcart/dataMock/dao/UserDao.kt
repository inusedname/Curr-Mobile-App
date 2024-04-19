package dev.vstd.shoppingcart.dataMock.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import dev.vstd.shoppingcart.dataMock.entity.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM UserEntity WHERE email = :email")
    fun getByEmail(email: String): UserEntity?

    @Insert
    fun insert(userEntity: UserEntity)

    @Update
    fun update(userEntity: UserEntity)
}