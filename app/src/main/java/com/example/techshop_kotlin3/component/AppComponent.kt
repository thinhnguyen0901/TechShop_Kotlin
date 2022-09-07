package com.example.techshop_kotlin3.component


import android.app.Application
import com.example.techshop_kotlin3.MyApplication
import com.example.techshop_kotlin3.module.ActivityBuildesModule
import com.example.techshop_kotlin3.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityBuildesModule::class])
interface AppComponent : AndroidInjector<MyApplication>  {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }



}