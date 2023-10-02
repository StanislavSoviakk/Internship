package com.example.task1_android_components.main

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import com.example.task1_android_components.R
import com.example.task1_android_components.base.BaseActivity
import com.example.task1_android_components.item_details.ItemDetailsFragment
import com.example.task1_android_components.model.Item
import com.example.task1_android_components.preferences.PreferencesManager
import com.example.task1_android_components.service.RunningService
import com.example.task1_android_components.utils.Constants

class MainActivity :
    BaseActivity<MainEvent, MainState, MainViewModel, MainViewModelFactory>(
        MainViewModel::class.java
    ) {

    override fun initUI() {
        setContentView(R.layout.activity_main)
        requestNotificationPermission()
        viewModel = createViewModel(
            MainViewModelFactory(
                PreferencesManager(applicationContext),
                MainReducer()
            )
        )
    }

    override fun initDATA() {
        getArguments()
    }

    override fun render(state: MainState) {
        if (state.lastOpenedItem != null) {
            openItemDetailsScreen(state.lastOpenedItem)
        }

    }

    private fun openItemDetailsScreen(selectedItem: Item) {
        val fragment = ItemDetailsFragment.newInstance(selectedItem)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .commit()
    }

    private fun getArguments() {
        val itemDetailsArgument = intent.getStringExtra(Constants.ITEM_DETAILS_ARGUMENT)
        if (itemDetailsArgument != null) {
            viewModel.handleArguments()
        }
    }

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                0
            )
        } else {
            createNotificationChannel()
            startService()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 0 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            createNotificationChannel()
            startService()
        }
    }

    private fun startService() {
        Intent(applicationContext, RunningService::class.java).also {
            it.action = RunningService.Actions.START.toString()
            startService(it)
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                Constants.NOTIFICATION_CHANNEL_ID,
                Constants.NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}