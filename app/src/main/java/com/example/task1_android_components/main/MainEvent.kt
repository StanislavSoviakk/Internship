package com.example.task1_android_components.main

import android.content.Context

sealed interface MainEvent {
    data class OpenItemDetails(val context: Context) : MainEvent
    data class GetLastOpenedItemId(val context: Context) : MainEvent
}