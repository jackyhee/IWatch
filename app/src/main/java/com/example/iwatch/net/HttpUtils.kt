package com.example.iwatch.net


import com.example.iwatch.net.callback.JsonCallback

import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import java.io.File

/**
 * Created by dillon on 2018/4/2.
 * app网络请求管理类
 */
object HttpUtils {

    fun <T> postJson(url: String, tag: Any, jsonStr: String, callback: JsonCallback<T>) {
        OkGo.post<T>(url)
            .tag(tag)
            .upJson(jsonStr)
            .execute(callback)

    }
    fun <T> uploadSingleFile(url: String, tag: Any, key: String, filePath: String, callback: JsonCallback<T>) {
        OkGo.post<T>(url)
            .tag(tag)
            .params(key,File(filePath))
            .execute(callback)

    }
    fun <T> uploadMultiFile(url: String, tag: Any, key: String,fileList: List<File>, callback: JsonCallback<T>) {
        OkGo.post<T>(url)
            .tag(tag)
            .addFileParams(key,fileList)
            .execute(callback)

    }
}
