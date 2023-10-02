package com.example.task1_android_components.model.use_cases

import com.example.task1_android_components.base.UseCase
import com.example.task1_android_components.items_list.ItemsListEvent
import com.example.task1_android_components.main.MainEvent
import com.example.task1_android_components.utils.Constants

class GetItemsListUseCase<INTENT, STATE> : UseCase<INTENT, STATE> {
    override fun invoke(event: INTENT, state: STATE): INTENT {
        when (event) {
            is ItemsListEvent.LoadItems -> {
                return ItemsListEvent.ItemsLoaded(Constants.getItemsList()) as INTENT
            }

            is MainEvent.LoadItems -> {
                return MainEvent.ItemsLoaded(Constants.getItemsList()) as INTENT
            }
        }
        throw IllegalArgumentException("Unexpected event: $event")

    }
}