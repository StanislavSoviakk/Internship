package com.example.task1_android_components.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task1_android_components.model.GetItemsListUseCase

class MainViewModel : ViewModel() {

    val getItemsListUseCase = GetItemsListUseCase()

    private val _state = MutableLiveData<MainState>()
    val state: LiveData<MainState> get() = _state

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.OpenItemDetails -> handleArguments(event.itemDetails, event.lastItemId)
        }
    }

    private fun handleArguments(itemDetailsArgument: String?, lastItemId: Int) {
        if (itemDetailsArgument != null) {
            if (lastItemId != -1) {
                val itemsList = getItemsListUseCase()
                _state.value = MainState(selectedItem = itemsList[lastItemId])
            }
        }
    }
}