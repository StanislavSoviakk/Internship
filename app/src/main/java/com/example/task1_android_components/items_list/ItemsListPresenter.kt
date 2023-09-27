package com.example.task1_android_components.items_list

import com.example.task1_android_components.model.GetItemsListUseCase
import com.example.task1_android_components.model.Item

class ItemsListPresenter(
    private val view: ItemsListContract.View,
    private val getItemsListUseCase: GetItemsListUseCase
) : ItemsListContract.Presenter {

    override fun loadItems() {
        val itemsList: List<Item> = getItemsListUseCase()
        view.showItems(itemsList = itemsList)
    }

    override fun onItemClick(item: Item) {
        view.saveItemInLocalStorage(itemId = item.id)
        view.showItemDetailsScreen(item = item)
    }
}