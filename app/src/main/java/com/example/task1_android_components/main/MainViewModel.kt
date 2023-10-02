package com.example.task1_android_components.main

import com.example.task1_android_components.base.BaseViewModel
import com.example.task1_android_components.model.use_cases.GetItemsListUseCase
import com.example.task1_android_components.preferences.PreferencesManager

class MainViewModel(
    private val preferencesManager: PreferencesManager, reducer: MainReducer
) : BaseViewModel<MainEvent, MainState>(
    reducer = reducer, useCasesList = listOf(GetItemsListUseCase())
) {

    fun handleArguments() {
        val lastItemId = getLastOpenedItemId()
        if (lastItemId != -1) {
            handleEvent(MainEvent.LoadItems)
            handleEvent(
                MainEvent.OpenItemDetailsScreen(
                    selectedItem = state.value?.itemsList?.get(
                        lastItemId
                    )
                )
            )
        }
    }

    private fun getLastOpenedItemId(): Int = preferencesManager.getLastOpenedItemId()


    override fun createInitialState(): MainState {
        return MainState(null, listOf())
    }
}