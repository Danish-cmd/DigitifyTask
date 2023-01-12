package com.danish.dxb.digitify.currency.conversion.common


import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class DataBindingRecyclerViewHolder<T : ViewDataBinding?>(val binding: T) : RecyclerView.ViewHolder(
    binding!!.root
)