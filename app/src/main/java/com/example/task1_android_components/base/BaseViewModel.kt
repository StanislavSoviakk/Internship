package com.example.task1_android_components.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<EVENT : BaseEvent, STATE : BaseState>(
    private val reducer: Reducer<STATE, EVENT>,
    private val useCasesList: List<UseCase<EVENT, STATE>>,
) : ViewModel() {

    private val _state = MutableLiveData(reducer.initState)
    val state: LiveData<STATE> = _state

    private val eventHandlers = mutableListOf<EventHandler<EVENT>>()

    fun handleEvent(event: EVENT) {
        _state.value = reducer.reduce(event, state.value ?: reducer.initState)

        eventHandlers.filter { it.canHandle(event) }.forEach { it.onEvent(event) }

        useCasesList.filter { it.canHandle(event) }.forEach {
            try {
                val result = it.invoke(event, state.value ?: reducer.initState)
                handleEvent(result)
            } catch (e: IllegalArgumentException) {
                Log.e("Event exception", e.message.toString())
            }
        }
    }

    fun doOnEvent(onEvent: (EVENT) -> Unit, canHandle: (EVENT) -> Boolean) {
        eventHandlers.add(EventHandler(onEvent = onEvent, canHandle = canHandle))
    }
}