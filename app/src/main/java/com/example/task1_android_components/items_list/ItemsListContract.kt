package com.example.task1_android_components.items_list

import com.example.task1_android_components.model.Item

interface ItemsListContract {
    interface View {
        fun showItems(itemsList: List<Item>)
        fun showItemDetailsScreen(item: Item)
    }

    interface Presenter {
        fun loadItems()

        fun onItemClick(item: Item)
    }
}