package com.example.task1_android_components.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

abstract class BaseActivity<EVENT : BaseEvent, STATE : BaseState, VM : BaseViewModel<EVENT, STATE>>(
    private val modelClass: Class<VM>
) : AppCompatActivity() {

    lateinit var viewModel: VM

    protected fun createViewModel(viewModelFactory: ViewModelProvider.Factory) {
        viewModel = ViewModelProvider(this, viewModelFactory)[modelClass]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initUI()
        initViewModel()
        initEVENT()
        initDATA()
    }

    abstract fun initUI()
    abstract fun initDATA()
    abstract fun initEVENT()
    abstract fun initViewModel()
}