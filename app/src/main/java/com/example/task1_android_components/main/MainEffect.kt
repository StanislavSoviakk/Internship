package com.example.task1_android_components.main

import com.example.task1_android_components.model.Item

sealed interface MainEffect {
    data class OpenItemDetailsScreen(val selectedItem: Item) : MainEffect
}