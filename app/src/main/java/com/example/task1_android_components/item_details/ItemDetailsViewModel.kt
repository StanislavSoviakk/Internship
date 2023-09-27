package com.example.task1_android_components.item_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemDetailsViewModel :
    ViewModel() {
    val itemId = MutableLiveData<Int>()
    val itemName = MutableLiveData<String>()
    val itemDescription = MutableLiveData<String>()
}