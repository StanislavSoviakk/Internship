package com.example.task1_android_components.base

class EventHandler<EVENT>(val onEvent: (EVENT) -> Unit, val canHandle: (EVENT) -> Boolean)