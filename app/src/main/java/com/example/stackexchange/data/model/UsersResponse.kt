package com.example.stackexchange.data.model

data class UsersResponse(
    val has_more: Boolean = false,
    val items: List<Item> = listOf(),
    val quota_max: Int = 0,
    val quota_remaining: Int = 0
)