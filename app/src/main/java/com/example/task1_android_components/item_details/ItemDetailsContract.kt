package com.example.task1_android_components.item_details

interface ItemDetailsContract {

    interface View {
        fun displayItemDetails()
    }

    interface Presenter {
        fun onViewCreated()
    }
}