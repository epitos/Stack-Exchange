package com.example.stackexchange.data.model

data class Tag(
    val count: Int = 0,
    val has_synonyms: Boolean = false,
    val is_moderator_only: Boolean = false,
    val is_required: Boolean = false,
    val name: String = ""
)