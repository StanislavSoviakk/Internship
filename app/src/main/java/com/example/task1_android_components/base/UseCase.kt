package com.example.task1_android_components.base

interface UseCase<EVENT, STATE> {
    fun canHandle(event: EVENT):Boolean
    fun invoke(event: EVENT, state: STATE): EVENT
}