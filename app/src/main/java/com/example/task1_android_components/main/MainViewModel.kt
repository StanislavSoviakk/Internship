package com.example.task1_android_components.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task1_android_components.model.GetItemsListUseCase
import com.example.task1_android_components.model.Item
import com.example.task1_android_components.preferences.PreferencesManager


class MainViewModel : ViewModel() {

    val getItemsListUseCase = GetItemsListUseCase()

    private val _selectedItem = MutableLiveData<Item>()
    val selectedItem: LiveData<Item> get() = _selectedItem

    fun openItemDetails(context: Context) {
        val pref = PreferencesManager(context)
        val lastItemId = pref.getLastOpenedItemId()
            if (lastItemId != -1) {
                val itemsList = getItemsListUseCase()
                _selectedItem.value = itemsList[lastItemId]
            }
    }
}