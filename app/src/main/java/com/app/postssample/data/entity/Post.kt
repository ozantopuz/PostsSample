package com.app.postssample.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(
    val userId : Int? = null,
    val id : Int? = null,
    val title : String? = null,
    val body : String? = null
) : Parcelable