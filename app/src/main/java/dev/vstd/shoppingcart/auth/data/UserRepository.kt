package dev.vstd.shoppingcart.auth.data

import dev.vstd.shoppingcart.auth.domain.UserInfo
import dev.vstd.shoppingcart.shopping.data.dao.CardDao
import dev.vstd.shoppingcart.shopping.data.entity.CardEntity
import dev.vstd.shoppingcart.shopping.data.repository.Response
import dev.vstd.shoppingcart.shopping.domain.UserCredential

class UserRepository(private val userDao: UserDao, private val cardDao: CardDao) {
    suspend fun login(email: String, password: String): Response<UserCredential> {
        val user = userDao.getByEmail(email) ?: return Response.Failed("User not found")
        return if (password == user.password) {
            Response.Success(UserCredential(user.id, user.username))
        } else {
            Response.Failed("Email or password is incorrect")
        }
    }

    suspend fun getUserInfo(userId: Long): UserInfo? {
        val user = userDao.getUserById(userId)
        return if (user != null) {
            UserInfo(username = user.username, email = user.email, address = user.address)
        } else {
            null
        }
    }

    suspend fun cashOut(userId: Long, amount: Int) {
        cardDao.cashOut(userId, amount)
    }

    suspend fun topUp(userId: Long, amount: Int) {
        cardDao.topUp(userId, amount)
    }

    suspend fun getAddress(userId: Long): String? {
        return userDao.getAddress(userId)
    }
    
    suspend fun updateAddress(userId: Long, address: String) {
        userDao.updateAddress(userId, address)
    }

    suspend fun signUp(username: String, email: String, password: String): Response<Unit> {
        val existedUser = userDao.findByEmailOrUsername(email, username = username)
        if (existedUser != null) return Response.Failed("User already exists")
        val user = UserEntity(username = username, email = email, password = password)
        userDao.insert(user)
        return Response.Success(Unit)
    }

    suspend fun getCards(userId: Long): List<CardEntity> {
        val cards = mutableListOf<CardEntity>()
        cardDao.getCard(userId)?.let { cards.add(it) }
        return cards
    }

    suspend fun registerCard(cardEntity: CardEntity): Response<Unit> {
        if (cardDao.getCard(cardEntity.userId) != null) return Response.Failed("Card already exists")
        cardDao.insert(cardEntity)
        return Response.Success(Unit)
    }
}