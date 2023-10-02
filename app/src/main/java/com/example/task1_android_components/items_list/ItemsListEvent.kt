package com.example.task1_android_components.items_list

import com.example.task1_android_components.base.BaseEvent
import com.example.task1_android_components.model.Item

sealed interface ItemsListEvent : BaseEvent {
    object LoadItems : ItemsListEvent
    data class SaveLastOpenedItemId(val itemId: Int) : ItemsListEvent
    object LastOpenedItemSaved : ItemsListEvent
    data class ItemsLoaded(val itemsList: List<Item>) : ItemsListEvent
}