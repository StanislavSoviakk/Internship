package com.example.task1_android_components.items_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.task1_android_components.databinding.FragmentItemsListItemBinding
import com.example.task1_android_components.model.Item

class MyItemRecyclerViewAdapter(
    private val onItemClick: (item: Item) -> Unit
) : ListAdapter<Item, MyItemRecyclerViewAdapter.ViewHolder>(ItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemsListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class ItemDiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(binding: FragmentItemsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val nameView: TextView = binding.name

        fun bind(item: Item) {
            nameView.text = item.name
            nameView.setOnClickListener {
                onItemClick(item)
            }
        }
    }
}