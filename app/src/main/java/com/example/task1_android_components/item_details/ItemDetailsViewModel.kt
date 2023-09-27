package com.example.task1_android_components.item_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemDetailsViewModel : ViewModel() {

    private val _state = MutableLiveData<ItemDetailsState>()
    val state: LiveData<ItemDetailsState> = _state

    fun onEvent(event: ItemDetailsEvent) {
        when (event) {
            is ItemDetailsEvent.LoadItem -> {
                _state.value = ItemDetailsState(event.item)
            }
        }
    }
}