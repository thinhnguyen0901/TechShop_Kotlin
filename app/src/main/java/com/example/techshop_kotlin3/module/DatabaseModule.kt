package com.example.techshop_kotlin3.module

import android.content.Context
import com.example.techshop_kotlin3.db.AppDao
import com.example.techshop_kotlin3.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun getAppDatabase(context: Context): AppDatabase{
        return AppDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun getAppDao(appDatabase: AppDatabase): AppDao{
        return appDatabase.appDao()
    }
}