package com.example.weiyue.component

import com.example.weiyue.ui.news.ImageBrowseActivity
import com.example.weiyue.ui.news.ArticleReadActivity
import com.example.weiyue.ui.video.DetailFragment
import com.example.weiyue.ui.news.NewsFragment
import dagger.Component

@Component(dependencies = [(ApplicationComponent::class)])
interface HttpComponent {

    fun inject(detailFragment: DetailFragment)

    fun inject(detailFragment: com.example.weiyue.ui.news.DetailFragment)

    fun inject(imageBrowseActivity: ImageBrowseActivity)

    fun inject(articleReadActivity: ArticleReadActivity)

    fun inject(newsFragment: NewsFragment)
}