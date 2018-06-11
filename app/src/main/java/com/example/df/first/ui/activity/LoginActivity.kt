package com.example.df.first.ui.activity

import com.example.df.first.base.BaseActivity
import com.example.df.first.base.Preference
import com.example.df.first.bean.LoginResponse
import com.example.df.first.constant.Constant
import com.example.df.first.view.LoginView

class LoginActivity : BaseActivity(), LoginView {

    private var isLogin: Boolean by Preference(Constant.LOGIN_KEY, false)

    private var user: String by Preference(Constant.PASSWORD_KEY, "")

    private var pwd: String by Preference(Constant.PASSWORD_KEY, "")

//    private val loginPresenter: Login
}