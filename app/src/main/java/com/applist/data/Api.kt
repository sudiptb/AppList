package com.applist.data

import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query
import sudipta.`in`.newwaytocode.network.dto.Data

interface Api {
    companion object {
        const val BASE_URL = "https://navkiraninfotech.com/g-mee-api/api/v1/"
    }

    @POST("apps/list")
    suspend fun getList(
        @Query("kid_id") id: String?
    ) : Response<AppListResponse.Response>



}