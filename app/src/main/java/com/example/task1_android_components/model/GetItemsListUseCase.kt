package com.example.task1_android_components.model

import com.example.task1_android_components.utils.Constants

class GetItemsListUseCase {
    operator fun invoke() = Constants.getItemsList()
}