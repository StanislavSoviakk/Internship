package com.example.task1_android_components.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment<EVENT : BaseEvent, STATE : BaseState, VM : BaseViewModel<EVENT, STATE>>(
    private val modelClass: Class<VM>
) : Fragment(), Renderer<STATE> {

    protected lateinit var viewModel: VM

    protected fun createViewModel(viewModelFactory: ViewModelProvider.Factory) {
        viewModel = ViewModelProvider(this, viewModelFactory)[modelClass]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
        initViewModel()
        viewModel.state.observe(viewLifecycleOwner) {
            render(it)
        }
        initData()

        super.onViewCreated(view, savedInstanceState)
    }

    abstract fun initViewModel()
    abstract fun initView()
    abstract fun initData()
}