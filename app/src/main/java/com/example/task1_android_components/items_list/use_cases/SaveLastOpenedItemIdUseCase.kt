package com.example.task1_android_components.items_list.use_cases

import com.example.task1_android_components.base.UseCase
import com.example.task1_android_components.items_list.ItemsListEvent
import com.example.task1_android_components.items_list.ItemsListState
import com.example.task1_android_components.preferences.PreferencesManager

class SaveLastOpenedItemIdUseCase(private val preferencesManager: PreferencesManager) :
    UseCase<ItemsListEvent, ItemsListState> {
    override fun invoke(event: ItemsListEvent, state: ItemsListState): ItemsListEvent {
        if (event is ItemsListEvent.SaveLastOpenedItemId) {
            preferencesManager.saveLastOpenedItemId(event.itemId)
            return ItemsListEvent.LastOpenedItemSaved
        }
        throw IllegalArgumentException("Unexpected event: $event")
    }
}