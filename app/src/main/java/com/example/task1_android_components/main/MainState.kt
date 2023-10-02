package com.example.task1_android_components.main

import com.example.task1_android_components.base.BaseState
import com.example.task1_android_components.model.Item

data class MainState(val lastOpenedItem: Item?, val itemsList: List<Item>) : BaseState