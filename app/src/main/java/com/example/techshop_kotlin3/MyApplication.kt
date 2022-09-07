package com.example.techshop_kotlin3

import android.app.Application
import com.example.techshop_kotlin3.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class MyApplication : DaggerApplication() {
    private val applicationInjector = DaggerAppComponent.builder().application(this).build()

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = applicationInjector

}

