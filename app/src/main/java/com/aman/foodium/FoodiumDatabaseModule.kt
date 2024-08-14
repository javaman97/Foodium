
package com.aman.foodium

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class FoodiumDatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application) = FoodiumPostsDatabase.getInstance(application)

    @Singleton
    @Provides
    fun providePostsDao(database: FoodiumPostsDatabase) = database.getPostsDao()
}
