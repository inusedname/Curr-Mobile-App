package dev.vstd.shoppingcart.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.vstd.shoppingcart.data.local.*
import dev.vstd.shoppingcart.data.remote.CheckoutRepository
import dev.vstd.shoppingcart.data.remote.CheckoutService
import dev.vstd.shoppingcart.data.remote.PaymentRepository
import dev.vstd.shoppingcart.data.remote.PaymentService
import dev.vstd.shoppingcart.dataMock.MockBackendDatabase
import okhttp3.Cache
import okhttp3.OkHttpClient
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) =
        AppDatabase.createDatabase(context)

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
        todoItemDao: TodoItemDao,
    ) = TodoRepository(todoGroupDao, todoItemDao)

    @Provides
    @Singleton
    fun providesBarcodeItemDao(appDatabase: AppDatabase) = appDatabase.barcodeItemDao

    @Provides
    @Singleton
    fun providesBarcodeRepository(barcodeItemDao: BarcodeItemDao) =
        BarcodeRepository(barcodeItemDao)

    @Provides
    @Singleton
    fun providesCheckoutService() = CheckoutService.build()

    @Provides
    @Singleton
    fun providesCheckoutRepository(checkoutService: CheckoutService) =
        CheckoutRepository(checkoutService)

    @Provides
    @Singleton
    fun providesPaymentService() = PaymentService.build()

    @Provides
    @Singleton
    fun providesPaymentRepository(paymentService: PaymentService) =
        PaymentRepository(paymentService)

    @Provides
    @Singleton
    fun providesOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val cache = context.cacheDir
        return OkHttpClient.Builder()
            .cache(Cache(cache, 10 * 1024 * 1024))
            .build()
    }

    @Provides
    @Singleton
    fun createBackendDatabase(@ApplicationContext context: Context) =
        MockBackendDatabase.create(context)
}