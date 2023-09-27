package com.example.task1_android_components.items_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task1_android_components.model.GetItemsListUseCase
import com.example.task1_android_components.model.Item

class ItemsListViewModel : ViewModel() {

    private val getItemsListUseCase = GetItemsListUseCase()

    private val _state = MutableLiveData<ItemsListState>()
    val state: LiveData<ItemsListState> = _state

    fun onEvent(event: ItemsListEvent) {
        when (event) {
            ItemsListEvent.LoadItems -> loadItems()
        }
    }

    fun loadItems() {
        val itemsList: List<Item> = getItemsListUseCase()
        _state.value = ItemsListState(itemsList)
    }
}