package com.example.techshop_kotlin3.ui.main

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainModule {
    @ContributesAndroidInjector
    abstract fun man1Fragment(): Man1Fragment

    @ContributesAndroidInjector
    abstract fun man2Fragment(): Man2Fragment

    @ContributesAndroidInjector
    abstract fun man3Fragment(): Man3Fragment
}