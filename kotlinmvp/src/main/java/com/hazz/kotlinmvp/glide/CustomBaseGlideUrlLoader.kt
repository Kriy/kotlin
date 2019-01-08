package com.hazz.kotlinmvp.glide

import com.bumptech.glide.load.Options
import com.bumptech.glide.load.model.*
import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader
import java.io.InputStream
import java.util.regex.Pattern

class CustomBaseGlideUrlLoader(concreateLoader: ModelLoader<GlideUrl, InputStream>,
                               modelCache: ModelCache<String, GlideUrl>) : BaseGlideUrlLoader<String>(concreateLoader, modelCache) {

    companion object {
        private val urlCache = ModelCache<String, GlideUrl>(150)
        /**
         * Url的匹配规则
         */
        private val PATTERN = Pattern.compile("__w-((?:-?\\d+)+)__")
    }

    /**
     * If the URL contains a special variable width indicator (eg "__w-200-400-800__")
     * we get the buckets from the URL (200, 400 and 800 in the example) and replace
     * the URL with the best bucket for the requested width (the bucket immediately
     * larger than the requested width).
     *
     * 控制加载的图片的大小
     */
    override fun getUrl(model: String, width: Int, height: Int, options: Options): String {
        val m = PATTERN.matcher(model)
        var bestBucket: Int
        if (m.find()) {
            val found = m.group(1).split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            for (s in found) {
                bestBucket = Integer.parseInt(s)
                if (bestBucket >= width) break
            }
        }
        return model
    }

    override fun handles(model: String): Boolean {
        return true
    }

    /**
     * 工厂来构建CustomBaseGlideUrlLoader对象
     */
    class Factory : ModelLoaderFactory<String, InputStream>{
        override fun build(multiFactory: MultiModelLoaderFactory): ModelLoader<String, InputStream> {
            return CustomBaseGlideUrlLoader(multiFactory.build(GlideUrl::class.java, InputStream::class.java), urlCache)
        }

        override fun teardown() {

        }
    }

}