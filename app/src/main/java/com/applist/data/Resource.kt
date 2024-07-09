package com.applist.data

import java.lang.Exception

sealed class Resource<out R: Any>{
    data class Success<out R: Any>(val result: R?): Resource<R>()
    data class Failure(val exception: Exception): Resource<Nothing>()
    data object Loading: Resource<Nothing>()
}
