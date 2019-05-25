package com.app.postssample.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.app.postssample.core.TestSchedulers
import com.app.postssample.core.rx.SchedulerProvider
import com.app.postssample.data.entity.Post
import com.app.postssample.data.repository.PostRepository
import com.app.postssample.scene.dashboard.DashboardActionProcessorHolder
import com.app.postssample.scene.dashboard.DashboardIntent
import com.app.postssample.scene.dashboard.DashboardViewModel
import com.app.postssample.scene.dashboard.DashboardViewState
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

class DashboardViewModelTest {
    @Mock
    private lateinit var repository: PostRepository
    private lateinit var schedulerProvider: SchedulerProvider
    private lateinit var viewModel: DashboardViewModel
    @Mock
    lateinit var observer: Observer<DashboardViewState>

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUpDashboardViewModel() {
        MockitoAnnotations.initMocks(this)
        schedulerProvider = TestSchedulers()
        viewModel = DashboardViewModel(DashboardActionProcessorHolder(repository, schedulerProvider))
        viewModel.states().observeForever(observer)
    }

    @Test
    fun test_getPosts_response_success() {
        val posts = listOf<Post>()

        whenever(repository.getPosts()).thenReturn(
            Single.just(listOf(Post(
                userId = 0,
                id = 0,
                title = "",
                body = ""
            ))))

        viewModel.processIntents(Observable.just(DashboardIntent.InitialIntent))
        verify(observer).onChanged(DashboardViewState(
            isLoading = true,
            isError = false,
            errorMessage = "",
            posts = emptyList()
        ))

        verify(observer).onChanged(DashboardViewState(
            isLoading = false,
            isError = false,
            errorMessage = "",
            posts = posts
        ))
    }

    @Test
    fun test_getPosts_response_failed() {
        whenever(repository.getPosts()).thenReturn(Single.error(Throwable("Error!")))

        viewModel.processIntents(Observable.just(DashboardIntent.InitialIntent))
        verify(observer).onChanged(DashboardViewState(
            isLoading = true,
            isError = false,
            errorMessage = "",
            posts = emptyList()
        ))

        verify(observer).onChanged(DashboardViewState(
            isLoading = false,
            isError = true,
            errorMessage = "Error!",
            posts = emptyList()
        ))
    }
}