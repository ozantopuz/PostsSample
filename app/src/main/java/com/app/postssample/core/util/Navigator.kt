package com.app.postssample.core.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.app.postssample.data.entity.Post

object Navigator{

    inline fun <reified T : Activity> Context.open() = startActivity(Intent(this, T::class.java))

    inline fun <reified T : Activity> Context.openWithPost(post : Post) {
        val intent = Intent(this, T::class.java)
        intent.putExtra("post", post)
        startActivity(intent)
    }

    inline fun <reified T : Activity> Activity.clearAndOpen() {
        val intent = Intent(this, T::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK and Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        this.finish()

    }
}