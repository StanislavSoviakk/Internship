package com.example.task1_android_components.items_list

import com.example.task1_android_components.base.BaseViewModel
import com.example.task1_android_components.items_list.use_cases.GetItemsListUseCase
import com.example.task1_android_components.items_list.use_cases.SaveLastOpenedItemIdUseCase
import com.example.task1_android_components.preferences.PreferencesManager

class ItemsListViewModel(
    preferencesManager: PreferencesManager, reducer: ItemsListReducer
) : BaseViewModel<ItemsListEvent, ItemsListState>(
    reducer = reducer,
    useCasesList = listOf(GetItemsListUseCase(), SaveLastOpenedItemIdUseCase(preferencesManager))
) {

    fun loadItems() {
        handleEvent(ItemsListEvent.LoadItems)
    }

    fun saveLastOpenedItemId(itemId: Int) {
        handleEvent(ItemsListEvent.SaveLastOpenedItemId(itemId))
    }
}