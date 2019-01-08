package com.hazz.kotlinmvp.net

class BaseResponse<T>(val code: Int,
                      val msg: String,
                      val data: T)