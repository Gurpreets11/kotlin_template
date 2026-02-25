package com.preet.kotlintemplate.utils

import java.util.Calendar

object AgeUtil {

    fun isAdult(dob: String, minimumAge: Int = 18): Boolean {

        try {
            val parts = dob.split("/")
            val day = parts[0].toInt()
            val month = parts[1].toInt() - 1
            val year = parts[2].toInt()

            val dobCalendar = Calendar.getInstance().apply {
                set(year, month, day)
            }

            val today = Calendar.getInstance()

            var age = today.get(Calendar.YEAR) - dobCalendar.get(Calendar.YEAR)

            if (today.get(Calendar.DAY_OF_YEAR) < dobCalendar.get(Calendar.DAY_OF_YEAR)) {
                age--
            }

            return age >= minimumAge

        } catch (e: Exception) {
            return false
        }
    }
}