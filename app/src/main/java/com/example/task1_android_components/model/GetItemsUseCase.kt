package com.example.task1_android_components.model

import com.example.task1_android_components.utils.Constants

class GetItemsUseCase {
    operator fun invoke() = Constants.getItemsList()
}