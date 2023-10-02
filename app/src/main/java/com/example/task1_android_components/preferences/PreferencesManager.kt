package com.example.task1_android_components.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.task1_android_components.utils.Constants

class PreferencesManager(
    context: Context
) {
    private val appContext = context.applicationContext

    private val preferences: SharedPreferences by lazy {
        appContext.getSharedPreferences(
            Constants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE
        )
    }

    fun saveLastOpenedItemId(itemId: Int) {
        preferences.edit {
            putInt(Constants.ITEM_ID_KEY, itemId)
        }
    }

    fun getLastOpenedItemId(): Int {
        return preferences.getInt(Constants.ITEM_ID_KEY, -1)
    }
}