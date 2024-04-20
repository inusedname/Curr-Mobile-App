package dev.vstd.shoppingcart.auth.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Query("SELECT * FROM UserEntity WHERE email = :email")
    suspend fun getByEmail(email: String): UserEntity?

    @Query("SELECT * FROM UserEntity WHERE email = :email OR username = :username")
    suspend fun findByEmailOrUsername(email: String, username: String): UserEntity?

    @Insert
    suspend fun insert(userEntity: UserEntity)

    @Update
    suspend fun update(userEntity: UserEntity)

    @Query("SELECT address FROM UserEntity WHERE id = :userId")
    suspend fun getAddress(userId: Long): String?
    
    @Query("UPDATE UserEntity SET address = :address WHERE id = :userId")
    suspend fun updateAddress(userId: Long, address: String)

    @Query("SELECT * FROM UserEntity WHERE id = :userId")
    suspend fun getUserById(userId: Long): UserEntity?
}