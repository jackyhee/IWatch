package com.example.iwatch

import android.app.Activity
import android.view.View
import com.blankj.utilcode.util.ActivityUtils
import com.example.iwatch.base.BaseActivity
import com.example.iwatch.base.BaseApplication
import com.example.iwatch.base.BaseApplication.Companion.tempContext
import com.example.iwatch.util.AppUtil
import com.vondear.rxtool.RxActivityTool
import com.vondear.rxtool.RxTool
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.tv_show_app
import kotlinx.android.synthetic.main.activity_main2.*
import org.jetbrains.anko.toast

class MainActivity : BaseActivity(), View.OnClickListener {
    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {}

    override fun initView() {}

    override fun initListener() {
        tv_hide_app.setOnClickListener(this)
        tv_show_app.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v) {
            tv_hide_app -> {
                AppUtil.hideAPP(this)
            }
            tv_show_app -> {
                AppUtil.showAPP(this)

            }
        }

    }

}
