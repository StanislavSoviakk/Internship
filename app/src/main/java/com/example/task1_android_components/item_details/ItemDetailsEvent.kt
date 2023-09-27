package com.example.task1_android_components.item_details

import com.example.task1_android_components.model.Item

sealed interface ItemDetailsEvent {
    data class LoadItem(val item: Item): ItemDetailsEvent
}