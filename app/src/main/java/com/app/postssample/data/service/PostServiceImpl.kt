package com.app.postssample.data.service

import com.app.postssample.data.entity.Comments
import com.app.postssample.data.entity.Post
import com.app.postssample.data.entity.User
import io.reactivex.Single

class PostServiceImpl(private val api: PostApi) : PostService {

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