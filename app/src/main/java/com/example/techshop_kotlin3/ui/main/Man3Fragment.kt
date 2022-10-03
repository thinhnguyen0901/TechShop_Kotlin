package com.example.techshop_kotlin3.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.work.WorkInfo
import com.example.data.model.Response
import com.example.techshop_kotlin3.R
import com.example.techshop_kotlin3.component.ViewModelFactory
import com.example.techshop_kotlin3.worker.ProductWorker
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


class Man3Fragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    private val viewModel: ProductViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_man3, container, false)
        return view
    }

    private var loadTv: TextView? = null
    private var loadLiveTv: TextView? = null
    private var btnCheck: TextView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadTv = view.findViewById(R.id.load_tv)
        loadLiveTv = view.findViewById(R.id.load_live_tv)
        btnCheck = view.findViewById(R.id.btn_check)

        view.findViewById<View>(R.id.btn_load).setOnClickListener {
            val current = viewModel.page.value ?: 0
            val next = current + 1
            onLoadDataOfPage(next)

            onStartWithWorker(next)
        }
        btnCheck?.setOnClickListener {

            //floww
            val data1 = viewModel.currentResponse

            //liveData
            val data2 = viewModel.productLiveResponse.value

        }
        onHandleProducts()
    }


    private fun onLoadDataOfPage(index: Int) {
        viewModel.page.value = index
    }

    @SuppressLint("SetTextI18n")
    private fun onHandleProducts() {
        viewModel.productLiveResponse.observe(viewLifecycleOwner, Observer {
            val currentPage = viewModel.page.value ?: 0
            val state = it.state
            loadLiveTv?.text = "LOADDATA FROM: $currentPage " + kotlin.run {
                when (state) {
                    Response.State.LOADING -> "Loading"
                    Response.State.SUCCESS -> "Success"
                    else -> "Error"
                }
            }

            when (state) {
                Response.State.LOADING -> {

                }
                Response.State.SUCCESS -> {

                }
                Response.State.ERROR -> {
                    // error của api , hoặc của lỗi cấu hình , hoặc ko có internet
                }
            }
        })

        lifecycleScope.launch {
            viewModel.productResponse.collectLatest {
                viewModel.currentResponse = it
                val currentPage = viewModel.page.value ?: 0
                val state = it.state
                loadTv?.text = "LOADDATA FROM: $currentPage " + kotlin.run {
                    when (state) {
                        Response.State.LOADING -> "Loading"
                        Response.State.SUCCESS -> "Success"
                        else -> "Error"
                    }
                }

                when (state) {
                    Response.State.LOADING -> {

                    }
                    Response.State.SUCCESS -> {

                    }
                    Response.State.ERROR -> {
                        // error của api , hoặc của lỗi cấu hình , hoặc ko có internet
                    }
                }
            }
        }
    }


    private fun onStartWithWorker(next: Int) {
        ProductWorker.create(requireContext(), page = next).observe(viewLifecycleOwner, Observer {
            when (it.first.state) {
                WorkInfo.State.ENQUEUED,
                WorkInfo.State.RUNNING -> {
                    //running
                    it.first.progress
                }
                WorkInfo.State.SUCCEEDED -> {
                    //success
                    it.second?.run {

                    }
                }
                else -> {
                    //fail
                }
            }
        })
    }
}