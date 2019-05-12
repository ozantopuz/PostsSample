package com.app.postssample.data.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Company(
    @SerializedName("name")
    @Expose
    val name : String? = null,
    @SerializedName("catchPhrase")
    @Expose
    val catchPhrase : String? = null,
    @SerializedName("bs")
    @Expose
    val bs : String? = null
)