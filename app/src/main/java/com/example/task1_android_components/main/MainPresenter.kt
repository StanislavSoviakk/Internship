package com.example.task1_android_components.main

import android.content.Context
import com.example.task1_android_components.model.GetItemsUseCase
import com.example.task1_android_components.preferences.PreferencesManager


class MainPresenter(
    private val view: MainContract.View,
    private val getItemsUseCase: GetItemsUseCase
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

    override fun handleArguments(itemDetailsArgument: String?, context: Context) {
        if (itemDetailsArgument != null) {
            val pref = PreferencesManager(context)
            val lastItemId = pref.getLastOpenedItemId()
            if (lastItemId != -1) {
                val itemsList = getItemsUseCase()
                view.showItemDetails(itemsList[lastItemId])
            }
        }
    }
}