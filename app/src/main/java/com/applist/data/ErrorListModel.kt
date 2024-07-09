package com.applist.data

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

class ErrorListModel {
    data class Response(
        @SerializedName("errors")
        val errorList: JsonObject
    )
}