package com.example.techshop_kotlin3.worker

import com.example.techshop_kotlin3.component.ChildWorkerFactory
import com.example.techshop_kotlin3.component.WorkerFactory
import com.example.techshop_kotlin3.component.WorkerKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class
WorkerModule {

    @Binds
    abstract fun bindWorkerFactory(workerFactory: WorkerFactory?): androidx.work.WorkerFactory?

    @Binds
    @IntoMap
    @WorkerKey(ProductWorker::class)
    abstract fun bindProductWorker(worker: ProductWorker.Factory?): ChildWorkerFactory?
}