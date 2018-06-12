package com.example.df.first.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.example.df.first.R
import com.example.df.first.base.BaseActivity
import com.example.df.first.base.Preference
import com.example.df.first.bean.LoginResponse
import com.example.df.first.constant.Constant
import com.example.df.first.presenter.LoginPresenterImpl
import com.example.df.first.view.LoginView
import com.gyf.barlibrary.ImmersionBar
import kotlinx.android.synthetic.main.activity_login.*
import toast

class LoginActivity : BaseActivity(), LoginView {

    private var isLogin: Boolean by Preference(Constant.LOGIN_KEY, false)

    private var user: String by Preference(Constant.PASSWORD_KEY, "")

    private var pwd: String by Preference(Constant.PASSWORD_KEY, "")

    private val loginPresenter: LoginPresenterImpl by lazy {
        LoginPresenterImpl(this)
    }

    override fun setLayoutId(): Int = R.layout.activity_login

    override fun initImmersionBar() {
        super.initImmersionBar()
        if (ImmersionBar.isSupportStatusBarDarkFont()) {
            immersionBar.statusBarDarkFont(true).init()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        login.setOnClickListener(onClickListener)
        register.setOnClickListener(onClickListener)
        loginExit.setOnClickListener(onClickListener)
    }

    override fun cancelRequest() {
        loginPresenter.cancelRequest()
    }

    override fun loginRegisterAfter(result: LoginResponse) {
        isLogin = true
        user = result.data.username
        pwd = result.data.password
        loginProgress.visibility = View.GONE
        setResult(
                Activity.RESULT_OK,
                Intent().apply { putExtra(Constant.CONTENT_TITLE_KEY, result.data.username) })
        finish()
    }

    override fun loginSuccess(result: LoginResponse) {
        toast("Login success")
    }

    override fun loginFailed(errorMessage: String?) {
        isLogin = false
        loginProgress.visibility = View.GONE
        errorMessage?.let { toast(it) }
    }

    override fun registerSuccess(result: LoginResponse) {
        toast("Register success")
    }

    override fun registerFailed(errorMessage: String?) {
        isLogin = false
        loginProgress.visibility = View.GONE
        username.requestFocus()
        errorMessage?.let { toast(it) }
    }

    private val onClickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.login -> {
                if (checkContent()) {
                    loginProgress.visibility = View.VISIBLE
                    loginPresenter.loginWanAndroid(
                            username.text.toString(),
                            password.text.toString()
                    )
                }
            }
            R.id.register -> {
                if (checkContent()) {
                    loginProgress.visibility = View.VISIBLE
                    loginPresenter.registerWanAndroid(
                            username.text.toString(),
                            password.text.toString(),
                            password.text.toString()
                    )
                }
            }
            R.id.loginExit -> {
                finish()
            }
        }
    }

    private fun checkContent(): Boolean {
        // error hint
        username.error = null
        password.error = null
        // cancel
        var cancel = false
        // attempt to view
        var focusView: View? = null
        // if register, check password confirm
        val pwdText = password.text.toString()
        val usernameText = username.text.toString()

        // check password
        if (TextUtils.isEmpty(pwdText)) {
            password.error = getString(R.string.password_not_empty)
            focusView = password
            cancel = true
        } else if (password.text.length < 6) {
            password.error = getString(R.string.password_length_short)
            focusView = password
            cancel = true
        }

        // check username
        if (TextUtils.isEmpty(usernameText)) {
            username.error = getString(R.string.username_not_empty)
            focusView = username
            cancel = true
        }

        // requestFocus
        if (cancel) {
            if (focusView != null) {
                focusView.requestFocus()
            }
            return false
        } else {
            return true
        }
    }

}