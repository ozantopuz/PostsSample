package com.app.postssample.scene.dashboard

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.postssample.R
import com.app.postssample.core.base.BaseActivity
import com.app.postssample.core.mvi.MviView
import com.app.postssample.core.util.Navigator.openWithPost
import com.app.postssample.scene.detail.DetailActivity
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_dashboard.*
import javax.inject.Inject

class DashboardActivity : BaseActivity(), MviView<DashboardIntent, DashboardViewState> {

    @Inject
    lateinit var factory: DashboardViewModelFactory

    private val clickIntent = PublishSubject.create<DashboardIntent.ClickIntent>()

    private val viewModel: DashboardViewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this, factory).get(DashboardViewModel::class.java)
    }

    override fun layoutId() = R.layout.activity_dashboard

    override fun activityInjector() = injector

    private fun initialIntent() = Observable.just(DashboardIntent.InitialIntent)

    override fun intents(): Observable<DashboardIntent> = Observable.merge(initialIntent(), clickIntent)

    override fun bind() {
        postRecyclerView.layoutManager = LinearLayoutManager(this)
        viewModel.processIntents(intents())
        viewModel.states().observe(this, Observer { if (it != null) render(it) })
    }


    override fun render(state: DashboardViewState) {
        with(state) {
            if (isLoading) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
            if (!posts.isEmpty()) {
                postRecyclerView.adapter = PostAdapter(posts) { post -> openWithPost<DetailActivity>(post) }
            }
        }
    }
}
