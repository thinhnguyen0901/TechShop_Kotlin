package com.example.techshop_kotlin3.component

import com.example.data.api.ApiModule
import com.example.data.repository.RepositoryModule
import com.example.data.room.DatabaseModule
import com.example.techshop_kotlin3.worker.WorkerModule
import dagger.Module

@Module(
    includes = [
        // module of Activity && ViewModel
        ActivityModule::class,
        ViewModelModule::class,

        // module of data
        ApiModule::class,
        RepositoryModule::class,
        DatabaseModule::class,

        // module of worker
        WorkerModule::class
    ]
)
class AppModule