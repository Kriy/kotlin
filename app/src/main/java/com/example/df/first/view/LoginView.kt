package com.example.df.first.view

import com.example.df.first.bean.LoginResponse

interface LoginView {

    fun loginSuccess(result: LoginResponse)

    fun loginFailed(errorMessage: String)

    fun registerSuccess(result: LoginResponse)

    fun registerFailed(errorMessage: String?)

    fun loginRegisterAfter(result: LoginResponse)
}