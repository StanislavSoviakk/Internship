package com.example.task1_android_components.main

sealed interface MainEvent {
    data class OpenItemDetails(val itemDetails: String?, val lastItemId: Int) : MainEvent
}