package com.applist.data

import com.google.gson.annotations.SerializedName
import sudipta.`in`.newwaytocode.network.dto.Data

interface AppListResponse {
    data class Response(
        @SerializedName("data")
        val data: Data? = null
    )
}