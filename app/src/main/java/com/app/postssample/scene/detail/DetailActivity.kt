package com.app.postssample.scene.detail

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.app.postssample.R
import com.app.postssample.core.base.BaseActivity
import com.app.postssample.core.extension.ignoreNull
import com.app.postssample.core.extension.toast
import com.app.postssample.core.mvi.MviView
import com.app.postssample.core.util.Constants.AVATAR_URL
import com.app.postssample.data.entity.Post
import com.bumptech.glide.Glide
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailActivity : BaseActivity(), MviView<DetailIntent, DetailViewState> {

    var post : Post? = null

    @Inject
    lateinit var factory: DetailViewModelFactory

    private val viewModel: DetailViewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this, factory).get(DetailViewModel::class.java)
    }

    override fun layoutId() = R.layout.activity_detail

    override fun activityInjector() = injector

    private fun initialUserIntent() : Observable<DetailIntent> = Observable.just(DetailIntent.InitialUserIntent)

    private fun initialCommentsIntent() : Observable<DetailIntent> = Observable.just(DetailIntent.InitialCommentsIntent)

    override fun intents(): Observable<DetailIntent> = Observable.merge(initialUserIntent(), initialCommentsIntent())

    override fun bind() {
        getIntentData()
        viewModel.processIntents(intents())
        viewModel.states().observe(this, Observer { if (it != null) render(it) })
    }

    override fun render(state: DetailViewState) {
        with(state) {
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE

            if (errorMessage.isNotEmpty()) toast(errorMessage)

            if (users.isNotEmpty()) {
                val user = users.find { it.id == post?.userId }
                Glide.with(this@DetailActivity).load(AVATAR_URL).into(imageView)
                nameTextView.text = user?.name.ignoreNull()
                usernameTextView.text = user?.username.ignoreNull()
                emailTextView.text = user?.email.ignoreNull()
            }

            if (comments.isNotEmpty()) {
                val commentCount = comments.filter { it.postId == post?.id }.size
                commentsTextView.text = commentCount.toString()
            }
        }
    }

    private fun getIntentData(){
        post = intent.getParcelableExtra("post")
        postTitleTextView.text = post?.title
        postBodyTextView.text = post?.body
    }
}
