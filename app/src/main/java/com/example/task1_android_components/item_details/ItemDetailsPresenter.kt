package com.example.task1_android_components.item_details

class ItemDetailsPresenter(private val view: ItemDetailsContract.View) :
    ItemDetailsContract.Presenter {
    override fun onViewCreated() {
        view.displayItemDetails()
    }
}