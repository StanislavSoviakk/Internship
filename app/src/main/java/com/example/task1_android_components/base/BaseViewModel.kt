package com.example.task1_android_components.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<EVENT : BaseEvent, STATE : BaseState>(
    private val reducer: Reducer<STATE, EVENT>,
    private val useCasesList: List<UseCase<EVENT, STATE>>
) : ViewModel() {

    private val _state = MutableLiveData<STATE>()
    val state: LiveData<STATE> = _state

    fun handleEvent(event: EVENT) {
        _state.value = reducer.reduce(event, state.value ?: createInitialState())

        useCasesList.forEach {
            try {
                val result = it.invoke(event, state.value ?: createInitialState())
                handleEvent(result)
            } catch (e: IllegalArgumentException) {
                // Use case doesn't handle this event, continue to the next one
            }
        }
    }

    abstract fun createInitialState(): STATE
}