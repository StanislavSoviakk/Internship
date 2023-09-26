package com.example.task1_android_components.utils

import com.example.task1_android_components.Item

object Constants {
    const val INTENT_ACTION = "NOTIFICATION_CLICKED"
    const val ITEM_DETAILS_ARGUMENT = "OPEN_FRAGMENT"
    const val ITEM_ID_KEY = "ITEM_ID"
    fun getItemsList():List<Item> = (0 until 20).map {
        Item(it, "$it name", "$it description")
    }
}