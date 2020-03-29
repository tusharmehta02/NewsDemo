package com.assignment.doubtnut.utility

import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun getFormattedDate(date: String): String {
        var convertedDate = ""
        try {
            val formatter =
                SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'", Locale.getDefault())
            formatter.timeZone = TimeZone.getTimeZone("UTC")
            val value = formatter.parse(date)

            val dateFormat = SimpleDateFormat("MMM dd, yyyy hh:mm", Locale.getDefault())
            dateFormat.timeZone = TimeZone.getDefault()
            convertedDate = dateFormat.format(value)
        } catch (e: Exception) {
            convertedDate = "00-00-0000"
        }

        return convertedDate
    }
}