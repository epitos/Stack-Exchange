package com.example.stackexchange.data.model

data class TagsResponse(
    val has_more: Boolean = false,
    val items: List<Tag> = listOf(),
    val quota_max: Int = 0,
    val quota_remaining: Int = 0
)