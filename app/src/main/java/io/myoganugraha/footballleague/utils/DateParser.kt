package io.myoganugraha.footballleague.utils

import java.text.SimpleDateFormat

class DateParser() {

    fun parser(date: String) : String {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")

            val unparsedDate = simpleDateFormat.parse(date)
            val newFormat = SimpleDateFormat("EEEE, MMM dd, yyyy")
            val parsedDate = newFormat.format(unparsedDate)

            return parsedDate

    }
}