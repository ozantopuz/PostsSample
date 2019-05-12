package com.app.postssample.data.service

import com.app.postssample.data.entity.Comments
import com.app.postssample.data.entity.Post
import com.app.postssample.data.entity.User
import io.reactivex.Single
import retrofit2.http.GET

interface PostApi {
    companion object {
        const val BASE_URL = "http://jsonplaceholder.typicode.com/"
    }

    @GET("posts")
    fun getPosts() : Single<List<Post>>

    @GET("users")
    fun getUsers() : Single<List<User>>

    @GET("comments")
    fun getComments() : Single<List<Comments>>
}