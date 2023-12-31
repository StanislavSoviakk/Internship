package com.example.task1_android_components

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.task1_android_components.item_details.ItemDetailsFragment
import com.example.task1_android_components.service.RunningService
import com.example.task1_android_components.utils.Constants

class MainActivity : AppCompatActivity() {

    private val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val itemDetailsArgument = intent.getStringExtra(Constants.ITEM_DETAILS_ARGUMENT)
        if (itemDetailsArgument != null) {
            launchItemDetails()
        }

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

    private fun launchItemDetails() {
        val itemsList = Constants.getItemsList()
        val itemId = sharedPreferences.getInt(Constants.ITEM_ID_KEY, -1)
        if (itemId != -1) {
            val fragment = ItemDetailsFragment.newInstance(itemsList[itemId])
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, fragment)
                .commit()
        }
    }

    override fun onStart() {
        super.onStart()
        startService()
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