package com.example.task1_android_components.items_list.use_cases

import com.example.task1_android_components.base.UseCase
import com.example.task1_android_components.items_list.ItemsListEvent
import com.example.task1_android_components.items_list.ItemsListState
import com.example.task1_android_components.utils.Constants

class GetItemsListUseCase : UseCase<ItemsListEvent, ItemsListState> {
    override fun invoke(event: ItemsListEvent, state: ItemsListState): ItemsListEvent {
        if (event is ItemsListEvent.LoadItems) {
            return ItemsListEvent.ItemsLoaded(Constants.getItemsList())
        }

        throw IllegalArgumentException("Unexpected event: $event")
    }

    override fun canHandle(event: ItemsListEvent): Boolean = event is ItemsListEvent.LoadItems
}