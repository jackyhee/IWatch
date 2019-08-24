package com.example.iwatch.net.callback

import com.google.gson.stream.JsonReader
import com.lzy.okgo.convert.Converter
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


class JsonConvert<T> : Converter<T> {

    private var type: Type? = null
    private var clazz: Class<T>?=null

    constructor() {}

    constructor(type: Type) {
        this.type = type
    }

    constructor(clazz: Class<T>) {
        this.clazz = clazz
    }

    /**
     * 该方法是子线程处理，不能做ui相关的工作
     * 主要作用是解析网络返回的 response 对象，生成onSuccess回调中需要的数据对象
     * 这里的解析工作不同的业务逻辑基本都不一样,所以需要自己实现,以下给出的时模板代码,实际使用根据需要修改
     */
    @Throws(Throwable::class)
    override fun convertResponse(response: Response): T? {
        if (type == null) {
            if (clazz == null) {
                // 如果没有通过构造函数传进来，就自动解析父类泛型的真实类型（有局限性，继承后就无法解析到）
                val genType = javaClass.genericSuperclass
                type = (genType as ParameterizedType).actualTypeArguments[0]
            } else {
                return parseClass(response, clazz)
            }
        }

        return if (type is Class<*>) {
            parseClass(response, type as Class<*>?)
        } else {
            parseType(response, type)
        }
    }

    @Throws(Exception::class)
    private fun parseClass(response: Response, rawType: Class<*>?): T? {
        if (rawType == null) return null
        val body = response.body() ?: return null
        val jsonReader = JsonReader(body.charStream())

        if (rawType == String::class.java) {

            return body.string() as T
        } else if (rawType == JSONObject::class.java) {

            return JSONObject(body.string()) as T
        } else if (rawType == JSONArray::class.java) {

            return JSONArray(body.string()) as T
        } else {
            val t = Convert.fromJson<T>(jsonReader, rawType)
            response.close()
            return t
        }
    }

    @Throws(Exception::class)
    private fun parseType(response: Response, type: Type?): T? {
        if (type == null) return null
        val body = response.body() ?: return null
        val jsonReader = JsonReader(body.charStream())

        // 泛型格式如下： new JsonCallback<任意JavaBean>(this)
        val t = Convert.fromJson<T>(jsonReader, type)
        response.close()
        return t
    }

}
