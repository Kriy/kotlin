package com.example.weiyue.module

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
class HttpModule {

    @Provides
    internal fun provideOkHttpClient(): OkHttpClient.Builder {
        // 指定缓存路径,缓存大小100Mb
        val cache = Cache(File(MyApp.instance.cacheDir, "HttpCache"),
                (1024 * 1024 * 100).toLong())
        return OkHttpClient().newBuilder().cache(cache)
                .retryOnConnectionFailure(true)
                .addInterceptor(RetrofitConfig.sLoggingInterceptor)
                .addInterceptor(RetrofitConfig.sRewriteCacheControlInterceptor)
                .addNetworkInterceptor(RetrofitConfig.sRewriteCacheControlInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
    }

    @Provides
    internal fun provideNetEaseApis(builder: OkHttpClient.Builder): NewsApi {
        builder.addInterceptor(RetrofitConfig.sQueryParameterInterceptor)

        val retrofitBuilder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())

        return NewsApi.getInstance(retrofitBuilder
                .baseUrl(ApiConstants.sIFengApi)
                .build().create(NewsApiService::class.java))
    }

    @Provides
    internal fun provideJanDanApis(builder: OkHttpClient.Builder): JanDanApi {

        val retrofitBuilder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())

        return JanDanApi.getInstance(retrofitBuilder
                .baseUrl(ApiConstants.sJanDanApi)
                .build().create(JanDanApiService::class.java))
    }

}