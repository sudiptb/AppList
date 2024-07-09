package com.applist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.applist.data.AppListResponse
import com.applist.data.Resource
import com.applist.repository.AppRepository
import com.applist.state.ItemState
import kotlinx.coroutines.launch

class MyViewModel(
    private val repository: AppRepository
): ViewModel() {

    private val _loginResponse: MutableLiveData<ItemState> = MutableLiveData()
    val loginResponse: LiveData<ItemState> get() = _loginResponse

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> get() = _loading


    init {
        getData()
    }


    fun getData(){
        println("getData")
        viewModelScope.launch {
            _loading.value = true
            val list = repository.getAppList("378")
            _loading.value = false
            when(list){
                is Resource.Success -> {

                    val res = list.result as AppListResponse.Response
                    println("response : ${res.data?.appList?.size}" )
                    res.data?.appList?.forEach {
                        println("item : ${it.app_name}")
                    }
                    res.data?.appList?.let {
                        _loginResponse.value = ItemState(itemList = it)
                    }

                }
                is Resource.Failure -> {
                    _loading.postValue(false)

                }
                is Resource.Loading -> {

                    _loading.postValue(false)
                }
            }
        }
    }

}