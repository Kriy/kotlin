package com.hazz.kotlinmvp.mvp.model.bean

import java.io.Serializable

data class CategoryBean(val id: Long,
                        val name: String,
                        val description: String,
                        val bgPicture: String,
                        val bgtColor: String,
                        val headerImage: String) : Serializable
