package com.example.task1_android_components.main

import androidx.lifecycle.ViewModel
import com.example.task1_android_components.model.GetItemsListUseCase
import com.example.task1_android_components.model.Item
import com.example.task1_android_components.preferences.PreferencesManager
import com.example.task1_android_components.utils.SingleLiveEvent

class MainViewModel(private val preferencesManager: PreferencesManager) : ViewModel() {

    val getItemsListUseCase = GetItemsListUseCase()

    private val selectedItem = SingleLiveEvent<Item>()
    fun getSelectedItem(): SingleLiveEvent<Item> {
        return selectedItem
    }

    fun openItemDetails() {
        val lastItemId = preferencesManager.getLastOpenedItemId()
        if (lastItemId != -1) {
            val itemsList = getItemsListUseCase()
            selectedItem.value = itemsList[lastItemId]
        }
    }
}