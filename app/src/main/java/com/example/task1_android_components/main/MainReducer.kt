package com.example.task1_android_components.main

import com.example.task1_android_components.base.Reducer

class MainReducer : Reducer<MainState, MainEvent> {

    override val initState: MainState = MainState(null)

    override fun reduce(event: MainEvent, state: MainState): MainState {
        return when (event) {
            is MainEvent.OpenItemDetailsScreen -> state.copy(
                lastOpenedItem = event.selectedItem
            )

            is MainEvent.LastItemLoaded -> state.copy(lastOpenedItem = event.item)
            is MainEvent.LoadLastItem -> state
            is MainEvent.NoLastItemFound -> state
        }
    }
}
