package com.app.postssample.data.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("userId")
    @Expose
    val userId : Int? = null,
    @SerializedName("id")
    @Expose
    val id : Int? = null,
    @SerializedName("title")
    @Expose
    val title : String? = null,
    @SerializedName("body")
    @Expose
    val body : String? = null
)