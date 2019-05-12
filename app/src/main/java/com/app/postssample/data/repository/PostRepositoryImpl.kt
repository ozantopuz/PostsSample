package com.app.postssample.data.repository

import com.app.postssample.data.entity.Comments
import com.app.postssample.data.entity.Post
import com.app.postssample.data.entity.User
import com.app.postssample.data.service.PostApi
import io.reactivex.Single

class PostRepositoryImpl(private val api: PostApi) : PostRepository {

    override fun getPosts(): Single<List<Post>> {
        return api.getPosts()
    }

    override fun getUsers(): Single<List<User>> {
        return api.getUsers()
    }

    override fun getComments(): Single<List<Comments>> {
        return api.getComments()
    }

}