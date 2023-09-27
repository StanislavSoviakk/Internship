package com.example.task1_android_components.main

import com.example.task1_android_components.model.Item

class MainContract {
    interface View {
        fun getArguments()
        fun showItemDetails(itemsList: List<Item>)
        fun requestNotificationPermission()
        fun createNotificationChannel()
        fun startService()
    }

    interface Presenter {
        fun onCreate()
        fun onStart()
        fun onPermissionGranted()
        fun handleArguments(itemDetailsArgument: String?)
    }
}