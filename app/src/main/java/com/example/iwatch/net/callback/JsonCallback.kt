package com.example.iwatch.net.callback

import com.lzy.okgo.callback.AbsCallback
import com.lzy.okgo.request.base.Request
import okhttp3.Response
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


abstract class JsonCallback<T> : AbsCallback<T> {

    private var type: Type? = null
    private var clazz: Class<T>?=null

    constructor() {}

    constructor(type: Type) {
        this.type = type
    }

    constructor(clazz: Class<T>) {
        this.clazz = clazz
    }

    override fun onStart(request: Request<T, out Request<*, *>>?) {
        super.onStart(request)
        // 主要用于在所有请求之前添加公共的请求头或请求参数
        // 例如登录授权的 token
        // 使用的设备信息
        // 可以随意添加,也可以什么都不传
        // 还可以在这里对所有的参数进行加密，均在这里实现
        //        request.headers("header1", "HeaderValue1")//
        //                .params("params1", "ParamsValue1")//
        //                .params("token", "3215sdf13ad1f65asd4f3ads1f");
    }

    /**
     * 该方法是子线程处理，不能做ui相关的工作
     * 主要作用是解析网络返回的 response 对象,生产onSuccess回调中需要的数据对象
     * 这里的解析工作不同的业务逻辑基本都不一样,所以需要自己实现,以下给出的时模板代码,实际使用根据需要修改
     */
    @Throws(Throwable::class)
    override fun convertResponse(response: Response): T? {
        if (type == null) {
            if (clazz == null) {
                val genType = javaClass.genericSuperclass
                type = (genType as ParameterizedType).actualTypeArguments[0]
            } else {
                val convert = JsonConvert(clazz!!)
                return convert.convertResponse(response)
            }
        }

        val convert = JsonConvert<T>(type!!)
        return convert.convertResponse(response)
    }
}
