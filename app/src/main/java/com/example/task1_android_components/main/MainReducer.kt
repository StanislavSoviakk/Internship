package com.example.task1_android_components.main

import com.example.task1_android_components.base.Reducer

class MainReducer : Reducer<MainState, MainEvent> {

    override fun reduce(event: MainEvent, state: MainState): MainState {
        return when (event) {
            is MainEvent.OpenItemDetailsScreen -> state.copy(
                lastOpenedItem = event.selectedItem
            )

            is MainEvent.ItemsLoaded -> {
                state.copy(itemsList = event.itemsList)
            }

            MainEvent.LoadItems -> state
        }
    }
}
