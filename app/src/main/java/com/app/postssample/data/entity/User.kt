package com.app.postssample.data.entity

data class User(
    val id : Int? = null,
    val name : String? = null,
    val username : String? = null,
    val email : String? = null,
    val address : Address? = null,
    val phone : String? = null,
    val website : String? = null,
    val company : Company? = null
)