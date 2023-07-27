package com.jhlee.rongame.common.utils

import java.text.SimpleDateFormat
import java.util.Date

class Utils {

    companion object {
        fun getCurrentDateInFormat(
            format: String = "yyyyMMdd", date: Long = System.currentTimeMillis()
        ): String {
            val date = Date(date)
            val sdf = SimpleDateFormat(format)
            return sdf.format(date)
        }
    }
}