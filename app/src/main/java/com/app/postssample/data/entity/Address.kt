package com.app.postssample.data.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("street")
    @Expose
    val street : String? = null,
    @SerializedName("suite")
    @Expose
    val suite : String? = null,
    @SerializedName("city")
    @Expose
    val city : String? = null,
    @SerializedName("zipcode")
    @Expose
    val zipcode : String? = null,
    @SerializedName("geo")
    @Expose
    val geo : Geo? = null
)