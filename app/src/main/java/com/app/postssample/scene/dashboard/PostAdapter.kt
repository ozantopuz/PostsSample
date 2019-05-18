package com.app.postssample.scene.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.postssample.R
import com.app.postssample.data.entity.Post
import kotlinx.android.synthetic.main.layout_item_post.view.*

class PostAdapter(private val dataSource: List<Post>, private val click:(Post)->Unit) : RecyclerView.Adapter<ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_item_post, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(dataSource[position]){
            holder.titleTextView?.text = title
            holder.bodyTextView?.text = body
            holder.itemView.setOnClickListener { click.invoke(this) }
        }
    }

    override fun getItemCount() = dataSource.size
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val titleTextView : TextView? = view.titleTextView
    val bodyTextView : TextView? = view.bodyTextView
}