package com.example.weiyue.net

import com.example.weiyue.bean.FreshNewsArticleBean
import com.example.weiyue.bean.FreshNewsBean
import com.example.weiyue.bean.JdDetailBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface JanDanApiService {

    @GET
    fun getFreshNews(@Url url: String, @Query("oxwlxojflwblxbsapi") oxwlxojflwblxbsapi: String,
                     @Query("include") include: String,
                     @Query("page") page: Int,
                     @Query("custom_fields") suctom_fields: String,
                     @Query("dev") dev: String
    ): Observable<FreshNewsBean>

    @GET
    fun getDetailData(@Url url: String, @Query("oxwlxojflwblxbsapi") oxwlxojflwblxbsapi: String,
                      @Query("page") page: Int
    ):Observable<JdDetailBean>

    @GET
    fun getFreshNewsArticle(@Url url: String, @Query("oxwlxojflwblxbsapi") oxwlxojflwblxbsapi: String,
                            @Query("include") include: String,
                            @Query("id") id: Int
    ):Observable<FreshNewsArticleBean>

}