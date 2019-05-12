package com.app.postssample.core.extension

fun String?.ignoreNull() =  this ?: ""

fun Int?.ignoreNull() =  this ?: 0

fun Long?.ignoreNull() =  this ?: 0

fun Double?.ignoreNull() =  this ?: 0.0

fun Boolean?.ignoreNull() =  this ?: false