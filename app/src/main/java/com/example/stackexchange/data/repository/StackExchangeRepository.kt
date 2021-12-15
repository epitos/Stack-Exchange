package com.example.stackexchange.data.repository

import com.example.stackexchange.data.api.ApiService
import javax.inject.Inject

class StackExchangeRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getUsers(orderBy: String, sortBy: String, name: String, site: String) =
        apiService.getUsers(orderBy, sortBy, name, site)

    suspend fun getTags(orderBy: String, sortBy: String, site: String) =
        apiService.getTags(orderBy, sortBy, site)
}