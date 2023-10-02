package com.example.task1_android_components.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

abstract class BaseActivity<EVENT : BaseEvent, STATE : BaseState, VM : BaseViewModel<EVENT, STATE>, VMFACTORY>(
    private val modelClass: Class<VM>
) : AppCompatActivity(), Renderer<STATE> {

    lateinit var viewModel: VM

    protected fun createViewModel(viewModelFactory: ViewModelProvider.Factory): VM {
        return ViewModelProvider(this, viewModelFactory)[modelClass]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initUI()
        viewModel.state.observe(this) {
            render(it)
        }
        initDATA()
    }

    abstract fun initUI()
    abstract fun initDATA()
}