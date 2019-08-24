package com.example.iwatch


import android.view.View

import com.example.iwatch.base.BaseActivity

import com.example.iwatch.util.AppUtil

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.tv_show_app

import org.jetbrains.anko.toast
import com.tbruyelle.rxpermissions2.RxPermissions
import android.Manifest.permission


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

                //大概7秒隐藏，看系统
                //因为隐藏后需要用拨号唤醒，所以在这先申请个权限
                val rxPermissions = RxPermissions(this)
                rxPermissions
                    .request(permission.READ_CALL_LOG,permission.WRITE_CALL_LOG)
                    .subscribe { granted ->
                        if (granted) {
                            toast("点击了隐藏")
                            AppUtil.hideAPP(this)
                        } else {
                            toast("请开启通话记录权限")
                        }
                    }
            }
            tv_show_app -> {
                toast("点击了显示")
                //大概7秒显示,看系统
                AppUtil.showAPP(this)

            }
        }

    }

}
