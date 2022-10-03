package com.example.techshop_kotlin3.component

import com.example.techshop_kotlin3.ui.main.MainActivity
import com.example.techshop_kotlin3.ui.main.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun contributeMainActivity(): MainActivity
}