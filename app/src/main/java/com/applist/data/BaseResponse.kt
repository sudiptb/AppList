package com.applist.data

import com.google.gson.Gson
import retrofit2.Response

data class BaseResponse<T>(val message: String?, val data: T?)

val <T> Response<T>.errorMessage
    get() = Gson().fromJson(errorBody()?.string(), BaseResponse::class.java).message
