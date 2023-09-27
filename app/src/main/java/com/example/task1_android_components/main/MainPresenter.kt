package com.example.task1_android_components.main

import com.example.task1_android_components.model.GetItemsListUseCase
import com.example.task1_android_components.preferences.PreferencesManager


class MainPresenter(
    private val view: MainContract.View,
    private val getItemsListUseCase: GetItemsListUseCase,
    private val preferences: PreferencesManager
) : MainContract.Presenter {

    override fun onPermissionGranted() {
        view.createNotificationChannel()
        view.startService()
    }

    override fun handleArguments(itemDetailsArgument: String?) {
        if (itemDetailsArgument != null) {
            val lastItemId = preferences.getLastOpenedItemId()
            if (lastItemId != -1) {
                val itemsList = getItemsListUseCase()
                view.showItemDetails(itemsList[lastItemId])
            }
        }
    }
}