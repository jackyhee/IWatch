package com.example.iwatch

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * @author dillon
 * @description:
 * @date :2019/8/22 16:30
 */
class InfoSecretCode : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action.equals("android.intent.action.NEW_OUTGOING_CALL")) {
            val str2 = "android.intent.extra.PHONE_NUMBER"
            val str1 = intent.getStringExtra(str2)
            if (str1.contains("879")) {
                resultData = null
                val activityIntent = Intent(context, MainActivity::class.java)
                activityIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(activityIntent)
            }

        }


    }
}