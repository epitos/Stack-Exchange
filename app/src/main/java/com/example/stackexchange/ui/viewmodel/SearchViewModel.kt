package com.example.stackexchange.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stackexchange.data.model.Item
import com.example.stackexchange.data.repository.StackExchangeRepository
import com.example.stackexchange.utils.Const.ORDER_BY_OPTION_ASC
import com.example.stackexchange.utils.Const.SITE_NAME_STACKOVERFLOW
import com.example.stackexchange.utils.NetworkHelper
import com.example.stackexchange.utils.Resource
import com.example.stackexchange.utils.Sort
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(val repository: StackExchangeRepository,
                                          val networkHelper: NetworkHelper): ViewModel() {

    val users: MutableLiveData<Resource<List<Item>>> by lazy {
        MutableLiveData<Resource<List<Item>>>()
    }

    fun getUsers(name: String) {
        viewModelScope.launch {
            users.postValue(Resource.loading(null))

            if (networkHelper.isNetworkConnected()) {
                repository.getUsers(ORDER_BY_OPTION_ASC, Sort.NAME.sortType, name,
                    SITE_NAME_STACKOVERFLOW).let {
                    if (it.isSuccessful) {
                        var userList = it.body()!!.items

                        if (userList.size > 20) {
                            userList = userList.take(20)
                        }
                        users.postValue(Resource.success(userList))
                    } else {
                        users.postValue(Resource.error(it.errorBody().toString(), null))
                    }
                }
            } else {
                users.postValue(Resource.error("No Internet Connection!", null))
            }
        }
    }
}