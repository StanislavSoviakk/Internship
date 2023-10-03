package com.example.task1_android_components.main.use_cases

import com.example.task1_android_components.base.UseCase
import com.example.task1_android_components.main.MainEvent
import com.example.task1_android_components.main.MainState
import com.example.task1_android_components.preferences.PreferencesManager
import com.example.task1_android_components.utils.Constants

class LoadLastItemUseCase(private val preferencesManager: PreferencesManager) :
    UseCase<MainEvent, MainState> {
    override fun invoke(event: MainEvent, state: MainState): MainEvent {
        if (event is MainEvent.LoadLastItem) {
            val lastItemId = preferencesManager.getLastOpenedItemId()
            if (lastItemId > -1) {
                val itemsList = Constants.getItemsList()
                itemsList.find { it.id == lastItemId }?.let { lastItem ->
                    return MainEvent.LastItemLoaded(lastItem)
                }
            }
            return MainEvent.NoLastItemFound
        }
        throw IllegalArgumentException("Unexpected event: $event")
    }

    override fun canHandle(event: MainEvent): Boolean = event is MainEvent.LoadLastItem
}