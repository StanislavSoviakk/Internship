package com.example.task1_android_components.items_list

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task1_android_components.model.GetItemsListUseCase
import com.example.task1_android_components.model.Item
import com.example.task1_android_components.preferences.PreferencesManager

class ItemsListViewModel : ViewModel() {

    private val getItemsListUseCase = GetItemsListUseCase()

    private val _state = MutableLiveData<ItemsListState>()
    val state: LiveData<ItemsListState> = _state

    fun onEvent(event: ItemsListEvent) {
        when (event) {
            ItemsListEvent.LoadItems -> loadItems()
            is ItemsListEvent.SaveLastOpenedItemId -> saveLastOpenedItemId(
                context = event.context,
                itemId = event.itemId
            )
        }
    }

    private fun loadItems() {
        val itemsList: List<Item> = getItemsListUseCase()
        _state.value = ItemsListState(itemsList)
    }

    private fun saveLastOpenedItemId(context: Context, itemId: Int) {
        val pref = PreferencesManager(context)
        pref.saveLastOpenedItemId(itemId)
    }
}