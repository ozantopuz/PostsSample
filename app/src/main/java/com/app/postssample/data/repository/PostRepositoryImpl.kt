package com.app.postssample.data.repository

import com.app.postssample.data.entity.Comments
import com.app.postssample.data.entity.Post
import com.app.postssample.data.entity.User
import com.app.postssample.data.service.PostService
import io.reactivex.Single

class PostRepositoryImpl(private val service: PostService) : PostRepository {

    override fun getPosts(): Single<List<Post>> {
        return service.getPosts()
    }

    override fun getUsers(): Single<List<User>> {
        return service.getUsers()
    }

    override fun getComments(): Single<List<Comments>> {
        return service.getComments()
    }

}