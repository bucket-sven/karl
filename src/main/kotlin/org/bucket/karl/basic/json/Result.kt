package org.bucket.karl.basic.json

class Result(var success: Boolean? = null,
             var message: String? = null,
             var item: Any? = null) {

    var error: String? = null

    companion object {
        fun error(message: String): Result {
            val r = Result(false, message, null)
            r.error = message
            return r
        }

        fun success(message: String): Result {
            return Result(true, message, null)
        }
    }
}