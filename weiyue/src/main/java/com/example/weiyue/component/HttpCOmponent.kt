package com.example.weiyue.component

import com.example.weiyue.ui.news.DetailFragment
import dagger.Component

@Component(dependencies = [(ApplicationComponent::class)])
interface HttpCOmponent {

    fun inject(detailFragment: DetailFragment)
}