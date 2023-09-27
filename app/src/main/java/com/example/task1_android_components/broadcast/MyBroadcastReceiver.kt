package com.example.task1_android_components.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.task1_android_components.main.MainActivity
import com.example.task1_android_components.utils.Constants

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Constants.INTENT_ACTION && context != null) {
            val openMainActivity = Intent(context, MainActivity::class.java)
            openMainActivity.putExtra(Constants.ITEM_DETAILS_ARGUMENT, "ItemDetailsFragment")
            openMainActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(openMainActivity)
        }
    }
}