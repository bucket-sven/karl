package org.bucket.karl.basic.utils

import java.text.SimpleDateFormat
import java.util.*

object Constants {
    const val USER_SESSION_ID = "user_session_id"
    val RESET_TIME = SimpleDateFormat("yyyyMMddhh").format(Date())
}