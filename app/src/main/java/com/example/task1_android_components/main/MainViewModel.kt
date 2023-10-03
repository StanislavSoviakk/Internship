package com.example.task1_android_components.main

import com.example.task1_android_components.base.BaseViewModel
import com.example.task1_android_components.main.use_cases.LoadLastItemUseCase
import com.example.task1_android_components.preferences.PreferencesManager

class MainViewModel(
    preferencesManager: PreferencesManager, reducer: MainReducer
) : BaseViewModel<MainEvent, MainState>(
    reducer = reducer,
    useCasesList = listOf(LoadLastItemUseCase(preferencesManager)),
) {

    fun loadLastOpenedItem() {
        handleEvent(MainEvent.LoadLastItem)
    }
}