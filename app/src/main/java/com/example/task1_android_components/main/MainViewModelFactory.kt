package com.example.task1_android_components.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.task1_android_components.preferences.PreferencesManager

class MainViewModelFactory(
    private val preferencesManager: PreferencesManager, private val reducer: MainReducer
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST") if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(preferencesManager, reducer) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}