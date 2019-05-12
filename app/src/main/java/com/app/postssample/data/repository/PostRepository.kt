package com.app.postssample.data.repository

import com.app.postssample.data.entity.Comments
import com.app.postssample.data.entity.Post
import com.app.postssample.data.entity.User
import io.reactivex.Single

interface PostRepository {

    fun getPosts() : Single<List<Post>>

    fun getUsers() : Single<List<User>>

    fun getComments() : Single<List<Comments>>
}