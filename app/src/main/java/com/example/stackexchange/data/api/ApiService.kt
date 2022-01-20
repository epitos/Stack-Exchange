package com.example.stackexchange.data.api

import com.example.stackexchange.utils.Const.INNAME
import com.example.stackexchange.utils.Const.ORDER_BY
import com.example.stackexchange.utils.Const.SITE
import com.example.stackexchange.utils.Const.SORT_BY
import com.example.stackexchange.utils.Const.TAGS
import com.example.stackexchange.utils.Const.USERS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(USERS)
    suspend fun getUsers(@Query(ORDER_BY) orderBy: String,
                         @Query(SORT_BY) sortBy: String,
                         @Query(INNAME) name: String,
                         @Query(SITE) site: String): Response<com.example.stackexchange.data.model.UsersResponse>

    @GET(TAGS)
    suspend fun getTags(@Query(ORDER_BY) orderBy: String,
                         @Query(SORT_BY) sortBy: String,
                         @Query(SITE) site: String): Response<com.example.stackexchange.data.model.TagsResponse>

}