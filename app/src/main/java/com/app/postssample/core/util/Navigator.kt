package com.app.postssample.core.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.app.postssample.core.extension.ignoreNull

object Navigator{

    inline fun <reified T : Activity> Context.open() = startActivity(Intent(this, T::class.java))

    inline fun <reified T : Activity> Context.openWithId(id : Int?) {
        val intent = Intent(this, T::class.java)
        intent.putExtra("id", id.ignoreNull())
        startActivity(intent)
    }

    inline fun <reified T : Activity> Context.clearAndOpen() {
        val intent = Intent(this, T::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK and Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}