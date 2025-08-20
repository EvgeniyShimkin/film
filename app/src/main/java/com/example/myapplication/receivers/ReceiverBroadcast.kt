package com.example.myapplication.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.myapplication.data.Enity.Film
import com.example.myapplication.view.notifications.NotificationConstants
import com.example.myapplication.view.notifications.NotificationHelper

class ReceiverBroadcast : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val bundle = intent?.getBundleExtra(NotificationConstants.FILM_BUNDLE_KEY)
        val film: Film = bundle?.get(NotificationConstants.FILM_KEY) as Film
        NotificationHelper.createNotification(context!!, film)
    }
}