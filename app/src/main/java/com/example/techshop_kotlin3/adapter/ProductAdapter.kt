package com.example.techshop_kotlin3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.techshop_kotlin3.R
import com.example.techshop_kotlin3.models.Product2


class ProductAdapter : PagingDataAdapter<Product2, ProductAdapter.ProductViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Product2>() {
            // The ID property identifies when items are the same.
            override fun areItemsTheSame(oldItem: Product2, newItem: Product2) =
                oldItem.id == newItem.id

            // If you use the "==" operator, make sure that the object implements
            // .equals(). Alternatively, write custom data comparison logic here.
            override fun areContentsTheSame(
                oldItem: Product2, newItem: Product2
            ) = oldItem == newItem
        }
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product,parent,false))
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        var txtName = holder.itemView.findViewById<TextView>(R.id.txtName)
        txtName.setText(getItem(position)?.name)
    }

}


