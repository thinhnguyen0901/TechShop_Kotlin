package com.example.techshop_kotlin3.worker

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.*
import com.example.data.model.Product2
import com.example.data.repository.product.ProductRepository
import com.example.techshop_kotlin3.component.ChildWorkerFactory
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.Duration
import java.util.*
import javax.inject.Inject


class ProductWorker(
    context: Context,
    workerParams: WorkerParameters,
    private val productRepository: ProductRepository
) : CoroutineWorker(context, workerParams) {

    // start worker : Return
    override suspend fun doWork(): Result {
        // Quy định số lần thử lại với workmanager
        if (runAttemptCount > 2) {
            return Result.failure()

        }
        // khởi chay
        val page = inputData.getInt(PAGE_INDEX_PARAMS, 1)

        kotlin.runCatching {
            productRepository.loadProductsSuspend(page).datas?.also { products ->
                return Result.success(Data.Builder().apply {
                    // khởi tạo dữ liệu output
                    val json = kotlin.runCatching { Gson().toJson(products) }.getOrNull()
                    this.putString(DATA_JSON, json)
                }.build())
            } ?: kotlin.run {
                return Result.retry()
            }
        }

        return Result.failure(Data.Builder().apply {
            // lỗi, lỗi bao code nhiêu, lỗi gì
            this.putInt(ERROR_CODE, 401)
        }.build())
    }


    // default khởi tạo
    class Factory @Inject constructor(
        private val productRepository: ProductRepository
    ) : ChildWorkerFactory {
        override fun create(
            appContext: Context,
            params: WorkerParameters
        ): CoroutineWorker {
            return ProductWorker(
                appContext,
                params,
                productRepository
            )
        }
    }

    companion object {

        // đang muốn thực hiện load products of a page
        private const val PAGE_INDEX_PARAMS = "PAGE_INDEX_PARAMS"
        private const val DATA_JSON = "DATA_JSON"
        private const val ERROR_CODE = "ERROR_CODE"

        fun createUUID(
            context: Context,
            page: Int
        ): UUID {
            // Khởi tạo 1 builder cho ProductWorker
            val workBuilder = OneTimeWorkRequest.Builder(ProductWorker::class.java)
            // truyền dữ liệu đầu vào cho worker
            workBuilder.setInputData(Data.Builder().apply {
                this.putInt(PAGE_INDEX_PARAMS, page)
            }.build())
            // khởi chạy worker:
            val oneTimeWorkRequest = workBuilder.apply {
                this.setInitialDelay(Duration.ofSeconds(5))
            }.build()
            WorkManager.getInstance(context).enqueue(oneTimeWorkRequest)
            // Hứng nội dung worker
            return oneTimeWorkRequest.id
        }

        fun observerUUid(
            context: Context,
            uuid: UUID
        ): LiveData<Pair<WorkInfo, MutableList<Product2>?>> {
            return Transformations.map(
                WorkManager.getInstance(context).getWorkInfoByIdLiveData(uuid)
            ) {
                // converts dữ liệu ra cái dữ liệu mong muốn
                val json = it?.outputData?.getString(DATA_JSON)
                val products = kotlin.runCatching {
                    val listType = object : TypeToken<MutableList<Product2>>() {}.type
                    val list: MutableList<Product2> = Gson().fromJson(json, listType)
                    list
                }.getOrNull()
                // return result theo từng trạng thái của work info
                Pair<WorkInfo, MutableList<Product2>?>(
                    it,
                    products
                )
            }
        }

        fun create(
            context: Context,
            page: Int
        ): LiveData<Pair<WorkInfo, MutableList<Product2>?>> {
            val uuid = createUUID(context, page)
            return observerUUid(context, uuid)
        }
    }
}