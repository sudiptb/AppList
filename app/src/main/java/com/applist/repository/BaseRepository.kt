package com.applist.repository

import android.util.Log
import com.applist.data.Resource
import com.applist.data.errorMessage
import retrofit2.Response

abstract class BaseRepository {

    suspend fun <R : Any> safeApiCall(call: suspend () -> Response<R>): Resource<R> =
        try {
            Log.e("request", "1")
            val response = call.invoke()
            if (response.isSuccessful) {
                Resource.Success(response.body())
            } else {
                Resource.Failure(Exception(response.errorMessage.toString()))
            }
        } catch (e: Exception) {
            Resource.Failure(Exception(e.localizedMessage))
        }


}