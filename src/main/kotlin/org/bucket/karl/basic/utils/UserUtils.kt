package org.bucket.karl.basic.utils

import org.bucket.karl.basic.entity.User
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

object UserUtils {
    val currentUser: User?
        get() {
            val request = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request
            val session = request.session
            val user = session.getAttribute(Constants.USER_SESSION_ID)
            return user as User?
        }
}
