package com.example.task1_android_components.base

interface Reducer<STATE, EVENT> {

    val initState: STATE
    fun reduce(event: EVENT, state: STATE): STATE
}