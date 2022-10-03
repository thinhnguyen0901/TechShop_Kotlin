package com.example.data.room

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun getAppDatabase(context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }
}