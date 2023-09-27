package com.example.task1_android_components.main

import com.example.task1_android_components.model.GetItemsListUseCase


class MainPresenter(
    private val view: MainContract.View,
    private val getItemsListUseCase: GetItemsListUseCase
) : MainContract.Presenter {
    override fun onCreate() {
        view.getArguments()
    }

    override fun onStart() {
        view.requestNotificationPermission()
    }

    override fun onPermissionGranted() {
        view.createNotificationChannel()
        view.startService()
    }

    override fun handleArguments(itemDetailsArgument: String?) {
        if (itemDetailsArgument != null) {
            val itemsList = getItemsListUseCase()
            view.showItemDetails(itemsList)
        }
    }
}