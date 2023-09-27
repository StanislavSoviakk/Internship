package com.example.task1_android_components.items_list

import android.content.Context
import com.example.task1_android_components.model.GetItemsListUseCase
import com.example.task1_android_components.model.Item
import com.example.task1_android_components.preferences.PreferencesManager

class ItemsListPresenter(
    private val view: ItemsListContract.View,
    private val getItemsListUseCase: GetItemsListUseCase
) : ItemsListContract.Presenter {

    override fun loadItems() {
        val itemsList: List<Item> = getItemsListUseCase()
        view.showItems(itemsList = itemsList)
    }

    override fun onItemClick(item: Item, context: Context) {
        saveLastOpenedItemId(itemId = item.id, context = context)
        view.showItemDetailsScreen(item = item)
    }

    private fun saveLastOpenedItemId(itemId: Int, context: Context) {
        val pref = PreferencesManager(context)
        pref.saveLastOpenedItemId(itemId)
    }
}
