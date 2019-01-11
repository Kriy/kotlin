package com.hazz.kotlinmvp.net.exception

object ErrorStatus {

    @JvmField
    val SUCCESS = 0

    @JvmField
    val UNKNOWN_ERROR = 1002

    @JvmField
    val SERVER_ERROR = 1003

    @JvmField
    val NETWORK_ERROR = 1004

    @JvmField
    val API_ERROR = 1005
}