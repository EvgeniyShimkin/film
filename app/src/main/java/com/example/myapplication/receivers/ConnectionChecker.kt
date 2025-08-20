package com.example.myapplication.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.myapplication.viewmodel.MainActivity

class ConnectionChecker (private val activity: MainActivity) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        //выход из метода если ноу интент
        if (intent == null) return
        when (intent.action) {
            //заряд менее 15
            Intent.ACTION_BATTERY_LOW ->{ Toast.makeText(context, "Батарея разряжена", Toast.LENGTH_SHORT).show()
            activity.enableDarkTheme(true)}
            //подкл к заряд
            Intent.ACTION_POWER_CONNECTED -> {
                Toast.makeText(context, "Зарядка подключена", Toast.LENGTH_SHORT).show()
                activity.enableDarkTheme(false)

        }
        }
    }
}