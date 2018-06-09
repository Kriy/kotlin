package com.example.df.first.constant

import android.widget.Toast

object Constant {

    const val REQUEST_BASE_URL = "http://wanandroid.com"

    const val SHARED_NAME = "_preferences"
    const val LOGIN_KEY = "login"
    const val USERNAME_KEY = "username"
    const val PASSWORD_KEY = "password"

    const val RESULT_NULL = "result null!"
    @JvmField
    var showToast: Toast? = null

    const val INTERCEPTOR_ENABLE = false
    const val CONTENT_URL_KEY = "url"
    const val CONTENT_TITLE_KEY = "title"
    const val CONTENT_ID_KEY = "id"
    const val CONTENT_CID_KEY = "cid"
    const val CONTENT_CHILDREN_DATA_KEY = "childrenData"
    const val CONTENT_TARGET_KEY = "target"

    const val CONTENT_SHARE_TYPE = "text/plain"
    const val SEAECH_KEY = "search"
    const val LIKE_KEY = "like"

    const val MAIN_REQUEST_CODE = 100
    const val MAIN_LIKE_REQUEST_CODE = 101
}