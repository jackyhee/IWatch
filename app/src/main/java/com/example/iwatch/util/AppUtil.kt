package com.example.iwatch.util

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager

import com.blankj.utilcode.util.AppUtils

/**
 * @author dillon
 * @description:
 * @date :2019/8/23 14:22
 */
object AppUtil {


    // 隐藏应用图标（包含Main.XML文件修改名称与修改图标）
    fun hideAPP(context: Context) {
        val packageManager = context.packageManager
        val mainStarterActivityComponentName = ComponentName(
            AppUtils.getAppPackageName(),
            AppUtils.getAppPackageName() + ".MainStarterActivity"
        )
        val res = packageManager.getComponentEnabledSetting(mainStarterActivityComponentName)
        if (res == PackageManager.COMPONENT_ENABLED_STATE_DEFAULT || res == PackageManager.COMPONENT_ENABLED_STATE_ENABLED) {
            packageManager.setComponentEnabledSetting(
                mainStarterActivityComponentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP
            )

        }
    }

    // 显示应用图标（包含Main.XML文件修改名称与修改图标）
    fun showAPP(context: Context) {
        val packageManager = context.packageManager
        val mainStarterActivityComponentName = ComponentName(
            AppUtils.getAppPackageName(),
            AppUtils.getAppPackageName() + ".MainStarterActivity"
        )
        val res = packageManager.getComponentEnabledSetting(mainStarterActivityComponentName)
        if (res == PackageManager.COMPONENT_ENABLED_STATE_DEFAULT || res == PackageManager.COMPONENT_ENABLED_STATE_ENABLED) {
        } else {
            packageManager.setComponentEnabledSetting(
                mainStarterActivityComponentName, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT,
                PackageManager.DONT_KILL_APP
            )

        }

    }
}
