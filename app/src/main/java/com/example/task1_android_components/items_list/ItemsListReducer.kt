package com.example.task1_android_components.items_list

import com.example.task1_android_components.base.Reducer

class ItemsListReducer : Reducer<ItemsListState, ItemsListEvent> {

    override val initState: ItemsListState = ItemsListState(listOf())

    override fun reduce(event: ItemsListEvent, state: ItemsListState): ItemsListState {
        return when (event) {
            is ItemsListEvent.LoadItems -> state
            is ItemsListEvent.SaveLastOpenedItemId -> state
            is ItemsListEvent.ItemsLoaded -> state.copy(itemsList = event.itemsList)
            is ItemsListEvent.LastOpenedItemSaved -> state
        }
    }
}