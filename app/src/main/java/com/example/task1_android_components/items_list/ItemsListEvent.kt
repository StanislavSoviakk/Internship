package com.example.task1_android_components.items_list

import android.content.Context

sealed interface ItemsListEvent {
    object LoadItems : ItemsListEvent
    data class SaveLastOpenedItemId(val context: Context, val itemId: Int) : ItemsListEvent
}