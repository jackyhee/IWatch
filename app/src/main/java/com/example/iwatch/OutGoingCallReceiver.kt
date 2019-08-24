package com.example.iwatch

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * @author dillon
 * @description:
 * @date :2019/8/22 16:30
 */
class OutGoingCallReceiver : BroadcastReceiver() {
    //这里写死唤醒码879，USSD格式，即拨打*879#，数字可以改，但是格式*开头，#结束，且APP有通话记录权限
    private val mCode = "879"

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action.equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
            val outCallNum = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER)
            if (outCallNum != null && outCallNum.contains(mCode)) {
                resultData = null
                val activityIntent = Intent(context, MainActivity::class.java)
                activityIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(activityIntent)
            }
        }

    }
}