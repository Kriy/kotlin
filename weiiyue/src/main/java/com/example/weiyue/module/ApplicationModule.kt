package com.example.weiyue.module

import android.content.Context
import com.example.weiyue.MyApp
import com.example.weiyue.net.*
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
@Module
class ApplicationModule(private val mContext: Context) {

    @Provides
    internal fun provideApplication(): MyApp {
        return mContext.applicationContext as MyApp
    }

    @Provides
    internal fun provideContext(): Context {
        return mContext
    }

}