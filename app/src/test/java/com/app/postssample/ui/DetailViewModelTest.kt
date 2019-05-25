package com.app.postssample.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.app.postssample.core.rx.SchedulerProvider
import com.app.postssample.core.ImmediateSchedulerProvider
import com.app.postssample.data.entity.Address
import com.app.postssample.data.entity.Comments
import com.app.postssample.data.entity.Company
import com.app.postssample.data.entity.User
import com.app.postssample.data.repository.PostRepository
import com.app.postssample.scene.detail.DetailActionProcessorHolder
import com.app.postssample.scene.detail.DetailIntent
import com.app.postssample.scene.detail.DetailViewModel
import com.app.postssample.scene.detail.DetailViewState
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class DetailViewModelTest {
    @Mock
    private lateinit var repository: PostRepository
    private lateinit var schedulerProvider: SchedulerProvider
    private lateinit var viewModel: DetailViewModel
    @Mock
    lateinit var observer: Observer<DetailViewState>

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUpDetailViewModel() {
        MockitoAnnotations.initMocks(this)
        schedulerProvider = ImmediateSchedulerProvider()
        viewModel = DetailViewModel(DetailActionProcessorHolder(repository, schedulerProvider))
        viewModel.states().observeForever(observer)
    }

    @Test
    fun test_getUsers_response_success() {
        val users = listOf<User>()

        whenever(repository.getUsers()).thenReturn(
            Single.just(listOf(User(
                id = 0,
                name = "",
                username = "",
                email = "",
                address = Address(),
                phone = "",
                website = "",
                company = Company()
            ))))

        viewModel.processIntents(Observable.just(DetailIntent.InitialUserIntent))
        verify(observer).onChanged(DetailViewState(
            isLoading = true,
            isError = false,
            errorMessage = "",
            users = emptyList(),
            comments = emptyList()
        ))

        verify(observer).onChanged(DetailViewState(
            isLoading = false,
            isError = false,
            errorMessage = "",
            users = users,
            comments = emptyList()
        ))
    }

    @Test
    fun test_getUsers_response_failed() {
        whenever(repository.getUsers()).thenReturn(Single.error(Throwable("Error!")))

        viewModel.processIntents(Observable.just(DetailIntent.InitialUserIntent))
        verify(observer).onChanged(DetailViewState(
            isLoading = true,
            isError = false,
            errorMessage = "",
            users = emptyList(),
            comments = emptyList()
        ))

        verify(observer).onChanged(DetailViewState(
            isLoading = false,
            isError = true,
            errorMessage = "Error!",
            users = emptyList(),
            comments = emptyList()
        ))
    }

    @Test
    fun test_getComments_response_success() {
        val comments = listOf<Comments>()

        whenever(repository.getComments()).thenReturn(
            Single.just(listOf(Comments(
                postId = 0,
                id = 0,
                name = "",
                email = "",
                body = ""
            ))))

        viewModel.processIntents(Observable.just(DetailIntent.InitialCommentsIntent))
        verify(observer).onChanged(DetailViewState(
            isLoading = true,
            isError = false,
            errorMessage = "",
            users = emptyList(),
            comments = emptyList()
        ))

        verify(observer).onChanged(DetailViewState(
            isLoading = false,
            isError = false,
            errorMessage = "",
            users = emptyList(),
            comments = comments
        ))
    }

    @Test
    fun test_getComments_response_failed() {
        whenever(repository.getComments()).thenReturn(Single.error(Throwable("Error!")))

        viewModel.processIntents(Observable.just(DetailIntent.InitialCommentsIntent))
        verify(observer).onChanged(DetailViewState(
            isLoading = true,
            isError = false,
            errorMessage = "",
            users = emptyList(),
            comments = emptyList()
        ))

        verify(observer).onChanged(DetailViewState(
            isLoading = false,
            isError = true,
            errorMessage = "Error!",
            users = emptyList(),
            comments = emptyList()
        ))
    }
}