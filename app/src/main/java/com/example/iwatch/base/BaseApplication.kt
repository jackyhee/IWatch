package com.example.iwatch.base

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDexApplication
import com.blankj.utilcode.util.Utils

import com.lzy.okgo.OkGo
import com.lzy.okgo.cache.CacheEntity
import com.lzy.okgo.cache.CacheMode
import com.lzy.okgo.cookie.CookieJarImpl
import com.lzy.okgo.cookie.store.DBCookieStore
import com.lzy.okgo.interceptor.HttpLoggingInterceptor
import com.sua.pocketsua.base.Constant

import com.tencent.bugly.crashreport.CrashReport
import com.vondear.rxtool.RxTool

import okhttp3.OkHttpClient
import java.net.Proxy
import java.util.concurrent.TimeUnit
import java.util.logging.Level

/**
 * Created by dillon on 2017/6/11.
 */

class BaseApplication : MultiDexApplication() {
companion object{
    var tempContext: Context?=null
}

    override fun onCreate() {
        super.onCreate()
        initBugly()
        Utils.init(this)
        RxTool.init(this)
        initOkGo()

    }


    private fun initOkGo() {
        val builder = OkHttpClient.Builder().proxy(Proxy.NO_PROXY)//防止抓包
        val loggingInterceptor = HttpLoggingInterceptor("OkGo")
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY)        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setColorLevel(Level.INFO)                               //log颜色级别，决定了log在控制台显示的颜色
        builder.addInterceptor(loggingInterceptor)                                 //添加OkGo默认debug日志
        //超时时间设置，默认60秒
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)      //全局的读取超时时间
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)     //全局的写入超时时间
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)   //全局的连接超时时间
        builder.cookieJar(CookieJarImpl(DBCookieStore(this)))              //使用数据库保持cookie，如果cookie不过期，则一直有效
        OkGo.getInstance().init(this) //必须调用初始化
                .setOkHttpClient(builder.build())               //必须设置OkHttpClient
                .setCacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST) //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE).retryCount = 3                    //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
    }

    private fun initBugly() {
        CrashReport.initCrashReport(applicationContext, Constant.BUGLY_APPSECRET, true)
    }

}