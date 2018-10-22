package com.example.weiyue.net

import com.example.weiyue.bean.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface NewsApiService {

    @GET("ClientNews")
    fun getNewsDetail(@Query("id") id: String,
                      @Query("action") action: String,
                      @Query("pullNum") pullNum: Int
    ): Observable<List<NewsDetail>>

    @GET("api_vampire_article_detail")
    fun getNewsArticleWithSub(@Query("aid") aid: String): Observable<NewsArticleBean>

    @GET
    fun getNewsArticleWithCmpp(@Url url: String,
                               @Query("aid") aid: String): Observable<NewsArticleBean>

    @GET
    fun getNewsImagesWithCmpp(@Url url: String,
                              @Query("aid") aid: String): Observable<NewsImagesBean>

    @GET("NewRelativeVideoList")
    fun getNewsVideoWithCmpp(@Url url: String,
                             @Query("guid") guid: String): Observable<NewsCmppVideoBean>

    @GET("ifengvideoList")
    fun getVideoChannel(@Query("page") page: Int): Observable<List<VideoChannelBean>>

    @GET("ifengvideoList")
    fun getVideoDetail(@Query("page") page: Int,
                       @Query("listtype") listtype: String,
                       @Query("typeid") typeid: String): Observable<List<VideoDetailBean>>

}