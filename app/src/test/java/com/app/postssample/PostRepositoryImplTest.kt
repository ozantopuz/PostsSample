package com.app.postssample

import com.app.postssample.data.entity.*
import com.app.postssample.data.repository.PostRepositoryImpl
import com.app.postssample.data.service.PostService
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PostRepositoryImplTest {
    @Mock
    private lateinit var service: PostService
    private lateinit var repository: PostRepositoryImpl
    @Before
    fun setUpRepository(){
        MockitoAnnotations.initMocks(this)
        repository = PostRepositoryImpl(service)
    }

    @Test
    fun test_getPosts_response(){
        //Given
        whenever(service.getPosts()).thenReturn(Single.just(postList))

        //When
        repository.getPosts()

        //Then
        Mockito.verify<PostService>(service).getPosts()
    }

    @Test
    fun test_getUsers_response(){
        //Given
        whenever(service.getUsers()).thenReturn(Single.just(userList))

        //When
        repository.getUsers()

        //Then
        Mockito.verify<PostService>(service).getUsers()
    }

    @Test
    fun test_getComments_response(){
        //Given
        whenever(service.getComments()).thenReturn(Single.just(commentList))

        //When
        repository.getComments()

        //Then
        Mockito.verify<PostService>(service).getComments()
    }

    companion object {
        private val postList = arrayListOf(Post(0, 0, "", ""))
        private val userList = arrayListOf(User(0, "", "", "", Address(), "", "", Company()))
        private val commentList = arrayListOf(Comments(0, 0, "", "", ""))
    }
}