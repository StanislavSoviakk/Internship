package com.example.task1_android_components.base

interface UseCase<EVENT, STATE> {
    fun invoke(event: EVENT, state: STATE): EVENT
}