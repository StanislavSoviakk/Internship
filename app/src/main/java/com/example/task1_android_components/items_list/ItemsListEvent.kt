package com.example.task1_android_components.items_list

sealed interface ItemsListEvent {
    object LoadItems : ItemsListEvent
}