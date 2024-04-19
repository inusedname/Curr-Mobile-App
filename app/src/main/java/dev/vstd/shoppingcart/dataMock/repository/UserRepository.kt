package dev.vstd.shoppingcart.dataMock.repository

import dev.vstd.shoppingcart.dataMock.dao.CardDao
import dev.vstd.shoppingcart.dataMock.dao.UserDao
import dev.vstd.shoppingcart.dataMock.entity.CardEntity
import dev.vstd.shoppingcart.dataMock.entity.UserEntity
import dev.vstd.shoppingcart.domain.UserCredential
import java.util.UUID

class UserRepository(private val userDao: UserDao, private val cardDao: CardDao) {
    fun login(email: String, password: String): Response<UserCredential> {
        val user = userDao.getByEmail(email) ?: return Response.Failed("User not found")
        return if (password == user.password) {
            Response.Success(UserCredential(user.id, user.username))
        } else {
            Response.Failed("Email or password is incorrect")
        }
    }

    fun signUp(username: String, email: String, password: String): Response<Unit> {
        val existedUser = userDao.findByEmailOrUsername(email, username = username)
        if (existedUser != null) return Response.Failed("User already exists")
        val user = UserEntity(username = username, email = email, password = password)
        userDao.insert(user)
        return Response.Success(Unit)
    }

    fun getCards(userId: Long): List<CardEntity> {
        val cards = mutableListOf<CardEntity>()
        cardDao.getCard(userId)?.let { cards.add(it) }
        return cards
    }

    fun registerCard(userId: Long, cardHolder: CardEntity.CardHolder): Response<Unit> {
        val existedCard = cardDao.getCard(userId)
        if (existedCard != null) {
            return Response.Failed("Card already registered")
        }
        cardDao.insert(CardEntity(
            userId = userId,
            cardHolder = cardHolder,
            cardNumber = UUID.randomUUID().toString().take(12),
            expirationDate = "12/25",
            cvv = "123"
        ))
        return Response.Success(Unit)
    }
}