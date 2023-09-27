package com.example.task1_android_components.main

import com.example.task1_android_components.model.Item

class MainContract {
    interface View {
        fun showItemDetails(item: Item)
        fun createNotificationChannel()
        fun startService()
    }

    interface Presenter {
        fun onPermissionGranted()
        fun handleArguments(itemDetailsArgument: String?)
    }
}