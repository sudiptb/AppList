package com.applist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.applist.R
import com.applist.databinding.ItemApplicationBinding
import com.bumptech.glide.Glide
import sudipta.`in`.newwaytocode.network.dto.Item

class ItemAdapter() : ListAdapter<Item, CustomViewHolder>(Companion) {

    companion object : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return true
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return true
        }

    }

    fun addData(moreList: List<Item>){
        submitList(moreList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemApplicationBinding.inflate(inflater, parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val currentItem = getItem(position)
        (holder.binding as ItemApplicationBinding).apply {
            model = currentItem
            Glide.with(holder.itemView.context)
                .load(currentItem.app_icon)
                .centerCrop()
                .placeholder(R.drawable.ic_user)
                .into(ivLogo)
        }
    }
}