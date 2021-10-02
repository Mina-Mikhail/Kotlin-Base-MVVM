package com.mina_mikhail.base_mvvm.presentation.base.extensions

import com.google.gson.Gson

fun Any.toJsonString(): String = Gson().toJson(this)

fun <A : Any> String.toJsonModel(modelClass: Class<A>): A = Gson().fromJson(this, modelClass)