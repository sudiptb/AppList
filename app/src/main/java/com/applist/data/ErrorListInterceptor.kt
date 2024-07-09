package com.applist.data

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ErrorListInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request()).apply {
            if (code == 422) {
                Gson().fromJson(body?.string(), ErrorListModel.Response::class.java)
                    .errorList.let { data ->
                        val error = StringBuilder().apply {
                            data.keySet().forEach {
                                data.get(it)
                                    .asJsonArray.forEach { element ->
                                        append(element.asString)
                                        append("\n")
                                    }
                            }
                        }
                        throw IOException(error.toString().trim())
                    }
            }
        }
    }
}