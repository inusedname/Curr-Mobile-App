package dev.vstd.shoppingcart.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.vstd.shoppingcart.data.local.AppDatabase
import dev.vstd.shoppingcart.data.local.TodoGroupDao
import dev.vstd.shoppingcart.data.local.TodoItemDao
import dev.vstd.shoppingcart.data.local.TodoRepository
import dev.vstd.shoppingcart.data.remote.CheckoutRepository
import dev.vstd.shoppingcart.data.remote.CheckoutService
import dev.vstd.shoppingcart.data.remote.PaymentRepository
import dev.vstd.shoppingcart.data.remote.PaymentService
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.createDatabase(context)
    }

    @Provides
    @Singleton
    fun provideTodoCategoryDao(appDatabase: AppDatabase) = appDatabase.todoGroupDao

    @Provides
    @Singleton
    fun provideTodoItemDao(appDatabase: AppDatabase) = appDatabase.todoItemDao

    @Provides
    @Singleton
    fun providesTodoRepository(
        todoGroupDao: TodoGroupDao,
        todoItemDao: TodoItemDao
    ): TodoRepository {
        return TodoRepository(todoGroupDao, todoItemDao)
    }

    @Provides
    @Singleton
    fun providesCheckoutService(): CheckoutService {
        return CheckoutService.build()
    }

    @Provides
    @Singleton
    fun providesCheckoutRepository(checkoutService: CheckoutService): CheckoutRepository {
        return CheckoutRepository(checkoutService)
    }

    @Provides
    @Singleton
    fun providesPaymentService(): PaymentService {
        return PaymentService.build()
    }

    @Provides
    @Singleton
    fun providesPaymentRepository(paymentService: PaymentService): PaymentRepository {
        return PaymentRepository(paymentService)
    }
}