package com.example.stackexchange.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    private const val DATE_FORMAT = "dd/MM/yyyy"

    fun toDate(creationDate: Long): String {
        val formatter = SimpleDateFormat(DATE_FORMAT)
        return formatter.format(Date(creationDate))
    }
}