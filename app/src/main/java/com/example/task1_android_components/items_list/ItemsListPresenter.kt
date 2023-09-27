package com.example.task1_android_components.items_list

import com.example.task1_android_components.model.GetItemsListUseCase
import com.example.task1_android_components.model.Item
import com.example.task1_android_components.preferences.PreferencesManager

class ItemsListPresenter(
    private val view: ItemsListContract.View,
    private val getItemsListUseCase: GetItemsListUseCase,
    private val preferences: PreferencesManager
) : ItemsListContract.Presenter {

    override fun loadItems() {
        val itemsList: List<Item> = getItemsListUseCase()
        view.showItems(itemsList = itemsList)
    }

    override fun onItemClick(item: Item) {
        saveLastOpenedItemId(itemId = item.id)
        view.showItemDetailsScreen(item = item)
    }

    private fun saveLastOpenedItemId(itemId: Int) {
        preferences.saveLastOpenedItemId(itemId)
    }
}
