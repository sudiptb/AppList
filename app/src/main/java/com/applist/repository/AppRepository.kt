package com.applist.repository

import com.applist.data.Api

class AppRepository(
    private val api: Api
): BaseRepository() {


    suspend fun getAppList(id: String?) = safeApiCall {
        api.getList(id)
    }

}