package com.example.task1_android_components.items_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.task1_android_components.preferences.PreferencesManager

class ItemsListViewModelFactory(
    private val preferencesManager: PreferencesManager,
    private val reducer: ItemsListReducer
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(ItemsListViewModel::class.java)) {
            return ItemsListViewModel(preferencesManager, reducer) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}