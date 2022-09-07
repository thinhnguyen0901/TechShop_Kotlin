package com.example.techshop_kotlin3.module

import com.example.techshop_kotlin3.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildesModule {
    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeMainActivity(): MainActivity
}