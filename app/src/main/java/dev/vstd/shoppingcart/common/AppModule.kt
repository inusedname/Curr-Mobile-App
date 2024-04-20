package dev.vstd.shoppingcart.common

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.vstd.shoppingcart.auth.data.UserRepository
import dev.vstd.shoppingcart.checklist.data.AppDatabase
import dev.vstd.shoppingcart.checklist.data.BarcodeRepository
import dev.vstd.shoppingcart.checklist.data.TodoRepository
import dev.vstd.shoppingcart.shopping.data.MockBackendDatabase
import dev.vstd.shoppingcart.shopping.data.repository.OrderRepository
import okhttp3.Cache
import okhttp3.OkHttpClient
import timber.log.Timber
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
    fun providesTodoRepository(appDatabase: AppDatabase): TodoRepository {
        return TodoRepository(appDatabase.todoGroupDao, appDatabase.todoItemDao)
    }

    @Provides
    @Singleton
    fun providesBarcodeRepository(appDatabase: AppDatabase): BarcodeRepository {
        return BarcodeRepository(appDatabase.barcodeItemDao)
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val cache = context.cacheDir
        return OkHttpClient.Builder()
            .readTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
            .writeTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
            .connectTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
            .addInterceptor {
                val request = it.request()
                val response = it.proceed(request)
                Timber.d("Request: ${request.url}")
                Timber.d("Response:${response.code}\n${response.peekBody(Long.MAX_VALUE).string()}")
                response
            }
            .cache(Cache(cache, 10 * 1024 * 1024))
            .build()
    }

    @Provides
    @Singleton
    fun providesUserRepository(backendDatabase: MockBackendDatabase): UserRepository {
        return UserRepository(backendDatabase.userDao, backendDatabase.cardDao)
    }

    @Provides
    @Singleton
    fun providesOrderRepository(backendDatabase: MockBackendDatabase): OrderRepository {
        return OrderRepository(backendDatabase.orderDao)
    }

    @Provides
    @Singleton
    fun createBackendDatabase(@ApplicationContext context: Context): MockBackendDatabase {
        return MockBackendDatabase.create(context)
    }
}