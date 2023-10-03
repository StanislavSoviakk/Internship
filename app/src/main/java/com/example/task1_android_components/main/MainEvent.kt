package com.example.task1_android_components.main

import com.example.task1_android_components.base.BaseEvent
import com.example.task1_android_components.model.Item

sealed interface MainEvent : BaseEvent {
    data class OpenItemDetailsScreen(val selectedItem: Item? = null) : MainEvent
    object LoadLastItem : MainEvent
    data class LastItemLoaded(val item: Item) : MainEvent
    object NoLastItemFound : MainEvent
}