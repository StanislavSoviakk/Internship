package com.example.task1_android_components.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task1_android_components.model.GetItemsListUseCase
import com.example.task1_android_components.preferences.PreferencesManager
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainViewModel(private val preferencesManager: PreferencesManager) : ViewModel() {

    val getItemsListUseCase = GetItemsListUseCase()

    private val _effect = Channel<MainEffect>()
    val effect = _effect.receiveAsFlow()

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.OpenItemDetails -> handleArguments()
        }
    }

    private fun handleArguments() {
        val lastItemId = getLastOpenedItemId()
        if (lastItemId != -1) {
            val itemsList = getItemsListUseCase()
            viewModelScope.launch {
                _effect.send(MainEffect.OpenItemDetailsScreen(itemsList[lastItemId]))
            }
        }
    }

    private fun getLastOpenedItemId(): Int =
        preferencesManager.getLastOpenedItemId()

}