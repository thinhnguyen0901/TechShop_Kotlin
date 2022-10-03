package com.example.techshop_kotlin3.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.techshop_kotlin3.R
import com.example.techshop_kotlin3.component.ViewModelFactory
import com.example.techshop_kotlin3.ui.main.adapter.ProductAdapter
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class Man2Fragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    private val viewModel: ProductViewModel by viewModels {
        viewModelFactory
    }

    var productAdapter = ProductAdapter()

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
        val view = inflater.inflate(R.layout.fragment_man2, container, false)
        viewModel.productDBLiveData.observe(viewLifecycleOwner, Observer { productAdapter.submitData(lifecycle,it)})

        val recyclerView = view.findViewById<RecyclerView>(R.id.rcProduct2)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = productAdapter
        }
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            Man2Fragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}