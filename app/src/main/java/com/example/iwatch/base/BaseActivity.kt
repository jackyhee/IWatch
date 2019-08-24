package com.example.iwatch.base

import android.app.Activity
import android.os.Bundle
import com.example.iwatch.util.StatusBarUtil


abstract class BaseActivity : Activity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseBeforeSetContentView()
        beforeSetContentView()
        setContentView(layoutId())
        baseInitData()
        initData()
        initView()
        initListener()
    }

    open fun beforeSetContentView() {}

    abstract fun layoutId(): Int

    abstract fun initData()

    abstract fun initView()

    abstract fun initListener()


    private fun baseBeforeSetContentView() {
        StatusBarUtil.setBarWhiteBackGroundBlackText(this)
    }

    private fun baseInitData() {

    }


}
