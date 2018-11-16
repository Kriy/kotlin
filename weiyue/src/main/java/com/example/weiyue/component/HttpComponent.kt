package com.example.weiyue.component

import com.example.weiyue.ui.jiandan.ImageBrowseActivity
import com.example.weiyue.ui.news.ArticleReadActivity
import com.example.weiyue.ui.news.DetailFragment
import dagger.Component

@Component(dependencies = [(ApplicationComponent::class)])
interface HttpComponent {

    fun inject(detailFragment: DetailFragment)

    fun inject(imageBrowseActivity: ImageBrowseActivity)

    fun inject(articleReadActivity: ArticleReadActivity)

}