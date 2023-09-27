package com.example.task1_android_components.items_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task1_android_components.model.GetItemsListUseCase
import com.example.task1_android_components.model.Item

class ItemsListViewModel : ViewModel() {

    private val getItemsListUseCase = GetItemsListUseCase()

    private val _itemsList = MutableLiveData<List<Item>>()
    val itemsList: LiveData<List<Item>> = _itemsList

    fun loadItems() {
        val itemsList: List<Item> = getItemsListUseCase()
        _itemsList.value = itemsList
    }
}