package com.example.df.first.presenter

import com.example.df.first.bean.LoginResponse
import com.example.df.first.model.HomeModel
import com.example.df.first.model.HomeModelImpl
import com.example.df.first.view.LoginView

class LoginPresenterImpl(private val loginView: LoginView): HomePresenter.OnLoginListener,
    HomePresenter.OnRegisterListener{

    private val homeModel: HomeModel = HomeModelImpl()

    override fun loginWanAndroid(username: String, password: String) {
        homeModel.loginWanAndroid(this, username, password)
    }

    override fun loginSuccess(result: LoginResponse) {
        if (result.errorCode != 0)
            loginView.loginFailed(result.errorMsg)
        else{
            loginView.loginSuccess(result)
            loginView.loginRegisterAfter(result)
        }
    }

    override fun loginFailed(errorMessage: String?) {
        loginView.loginFailed(errorMessage)
    }

    override fun registerWanAndroid(username: String, password: String, repassword: String) {
        homeModel.registerWanAndroid(this, username, password, repassword)
    }

    override fun registerSuccess(result: LoginResponse) {
        if (result.errorCode != 0) {
            loginView.registerFailed(result.errorMsg)
        }else{
            loginView.registerSuccess(result)
            loginView.loginRegisterAfter(result)
        }
    }

    override fun registerFailed(errorMessage: String?) {
        loginView.registerFailed(errorMessage)
    }

    fun cancelRequest(){
        homeModel.cancelLoginRequest()
        homeModel.cancelRegisterRequest()
    }


}