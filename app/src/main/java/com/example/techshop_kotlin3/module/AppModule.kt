package com.example.techshop_kotlin3.module

import dagger.Module

@Module(
    includes = [
        ViewModelModule::class,
        RetrofitModule::class,
        DataModule::class,
        DatabaseModule::class
    ]
)
class AppModule