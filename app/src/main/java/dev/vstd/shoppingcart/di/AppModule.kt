package dev.vstd.shoppingcart.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.vstd.shoppingcart.data.local.AppDatabase
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
    fun provideTodoCategoryDao(appDatabase: AppDatabase) = appDatabase.todoCategoryDao

    @Provides
    @Singleton
    fun provideTodoItemDao(appDatabase: AppDatabase) = appDatabase.todoItemDao
}