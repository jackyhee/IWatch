package com.example.iwatch.base

import java.io.Serializable


/**
 * Created by dillon on 2017/6/16.
 */

open class BaseResponse<T> :Serializable{
    var code: Int = 0//状态码
    var msg: String = ""//信息
    var data: T? = null
}
