package com.example.df.first.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import top.jowanxu.wanandroidclient.ui.activity.MainActivity

class SplashActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}