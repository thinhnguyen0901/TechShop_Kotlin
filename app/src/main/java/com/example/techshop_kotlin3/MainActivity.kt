package com.example.techshop_kotlin3

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.techshop_kotlin3.adapter.ProductAdapter


import com.example.techshop_kotlin3.viewmodel.ProductViewModel
import com.example.techshop_kotlin3.viewmodel.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: ProductViewModel by viewModels {
        viewModelFactory
    }

    var productAdapter = ProductAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*viewModel = ViewModelProviders.of(this).get(ProductViewModel::class.java)
        viewModel.refreshProduct()
        viewModel.productLiveData.observe(this) {
            Log.d("MainActivity ", "productLiveData " + (it?.toString() ?: ""))
        }*/


        //(application as MyApplication).component.inject(this)


        /*viewModel = ViewModelProvider(this, viewModelFactory).get(ProductViewModel::class.java)
        viewModel.productLiveData.observe(this) {
            Log.d("MainActivity ", "productLiveData " + (it?.toString() ?: ""))
        }*/


        viewModel.productLiveData.observe(this) {
            Log.d("MainActivity ", "productLiveData " + (it?.toString() ?: ""))
        }

        //viewModel.list.observe(this, Observer { productAdapter.submitList(it) })

        viewModel.productLiveData2.observe(this, Observer { productAdapter.submitData(lifecycle,it)})

        val recyclerView = findViewById<RecyclerView>(R.id.rcProduct)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = productAdapter
        }

    }


}