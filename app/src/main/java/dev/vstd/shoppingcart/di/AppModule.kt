package dev.vstd.shoppingcart.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.vstd.shoppingcart.data.local.*
import dev.vstd.shoppingcart.dataMock.MockBackendDatabase
import dev.vstd.shoppingcart.dataMock.repository.UserRepository
import okhttp3.Cache
import okhttp3.OkHttpClient
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
    fun createBackendDatabase(@ApplicationContext context: Context): MockBackendDatabase {
        return MockBackendDatabase.create(context)
    }
}