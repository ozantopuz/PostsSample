package com.app.postssample.data.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    @Expose
    val id : Int? = null,
    @SerializedName("name")
    @Expose
    val name : String? = null,
    @SerializedName("username")
    @Expose
    val username : String? = null,
    @SerializedName("email")
    @Expose
    val email : String? = null,
    @SerializedName("address")
    @Expose
    val address : String? = null,
    @SerializedName("phone")
    @Expose
    val phone : String? = null,
    @SerializedName("website")
    @Expose
    val website : String? = null,
    @SerializedName("company")
    @Expose
    val company : Company? = null
)