package com.example.stackexchange.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stackexchange.data.model.Tag
import com.example.stackexchange.data.repository.StackExchangeRepository
import com.example.stackexchange.utils.Const
import com.example.stackexchange.utils.Const.ORDER_BY_OPTION_ASC
import com.example.stackexchange.utils.NetworkHelper
import com.example.stackexchange.utils.Resource
import com.example.stackexchange.utils.Sort
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserDetailViewModel @Inject constructor(val repository: StackExchangeRepository,
                                             val networkHelper: NetworkHelper): ViewModel() {
    val tags: MutableLiveData<Resource<List<Tag>>> by lazy {
        MutableLiveData<Resource<List<Tag>>>()
    }

    init {
        getTags()
    }

    private fun getTags() {
        viewModelScope.launch {
            tags.postValue(Resource.loading(null))

            if (networkHelper.isNetworkConnected()) {
                repository.getTags(ORDER_BY_OPTION_ASC, Sort.POPULAR.sortType,
                    Const.SITE_NAME_STACKOVERFLOW).let {
                    if (it.isSuccessful) {
                        tags.postValue(Resource.success(it.body()!!.items))
                    } else {
                        tags.postValue(Resource.error(it.errorBody().toString(), null))
                    }
                }
            } else {
                tags.postValue(Resource.error("No Internet Connection!", null))
            }
        }
    }
}